package com.eliel.inotes.service;

import com.eliel.inotes.model.User;
import com.eliel.inotes.repo.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User AuthUser(){
        return  (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
