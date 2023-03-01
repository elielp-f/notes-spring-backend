package com.eliel.inotes.service;

import com.eliel.inotes.DTO.LoginDTO;
import com.eliel.inotes.DTO.RegisterDTO;
import com.eliel.inotes.DTO.ResponseDTO;
import com.eliel.inotes.exceptions.BadCredentialsException;
import com.eliel.inotes.exceptions.EmailTakenException;
import com.eliel.inotes.model.User;
import com.eliel.inotes.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service @RequiredArgsConstructor
public class AuthService {
    private final UserRepository repository;
    private final JWTService jwtService;
    private final AuthenticationManager manager;
    private final PasswordEncoder passwordEncoder;
    public ResponseDTO register(RegisterDTO dto) throws EmailTakenException {
        if(!EmailTaken(dto.getEmail())){
            User user = User.builder()
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .build();
            repository.save(user);
            authenticate(dto.getEmail(), dto.getPassword());
            return ResponseDTO.builder()
                    .token(jwtService.generateToken(user))
                    .build();
        }
        throw new EmailTakenException("Email taken.");
    }

    public ResponseDTO login(LoginDTO dto) {
        if(authenticate(dto.getEmail(), dto.getPassword())){
            throw new BadCredentialsException("bad credentials");
        }
        User user = repository.findByEmail(dto.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        return ResponseDTO.builder()
                .token(token)
                .build();
    }

    private boolean EmailTaken(String email){
        return repository.existsByEmail(email);
    }

    private boolean authenticate(String email, String password){
        boolean badCredentials = false;
        try{
            manager.authenticate(new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>()));
        }
        catch (Exception ex){
            if(SecurityContextHolder.getContext().getAuthentication() != null){
                SecurityContextHolder.clearContext();
            }
            badCredentials = true;
        }
        return badCredentials;
    }
}
