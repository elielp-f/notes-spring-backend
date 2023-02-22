package com.notes.notes.services;

import com.notes.notes.expeptions.EmailTaken;
import com.notes.notes.expeptions.UserNotFound;
import com.notes.notes.modals.User;
import com.notes.notes.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String firstName, String lastName, String email, String password) throws Exception {
        if(!isValidEmail(email)){
            throw new EmailTaken("email taken!");
        }
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setHash(hash_password(password));
        return this.userRepository.save(user);
    }

    public String hash_password(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password);
        return hash;
    }

    public boolean isValidEmail(String email){
        return !this.userRepository.existsByEmail(email);
    }

    public User editUser(String firstName, String lastName, String email, String password, String id){
        User user = userRepository.findById(id).orElseThrow(() -> {throw new UserNotFound("User not found!");});
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setHash(hash_password(password));
        return this.userRepository.save(user);
    }

    public void deleteUser(String id){
        if(this.userRepository.existsById(id)){
            this.userRepository.deleteById(id);
        }
        else{
            throw new UserNotFound("User not found!");
        }
    }
}
