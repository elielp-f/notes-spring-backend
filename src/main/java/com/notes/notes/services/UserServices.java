package com.notes.notes.services;

import com.notes.notes.modals.User;
import com.notes.notes.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServices {
    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String firstName, String lastName, String email, String password){
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
}
