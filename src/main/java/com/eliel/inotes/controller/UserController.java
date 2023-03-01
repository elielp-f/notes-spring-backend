package com.eliel.inotes.controller;

import com.eliel.inotes.DTO.LoginDTO;
import com.eliel.inotes.DTO.RegisterDTO;
import com.eliel.inotes.DTO.ResponseDTO;
import com.eliel.inotes.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {
    private final AuthService auth;
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(@Valid @RequestBody RegisterDTO dto){
        return ResponseEntity.ok(auth.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginDTO dto){
        return ResponseEntity.ok(auth.login(dto));
    }
}
