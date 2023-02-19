package com.notes.notes.controllers;

import com.notes.notes.modals.User;
import com.notes.notes.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/")
    public String helloworld(){
        return "hello world";
    }
    @PostMapping("/signup")
    public String signup(@RequestBody @Valid User user){
        User userSaved = this.userServices.createUser(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getHash()
        );
        return String.format("Welcome %s %s to THE NOTES your id is %s", userSaved.getFirstName(), userSaved.getLastName(), userSaved.getId());
    }

    @PostMapping("/login")
    public String login(@RequestBody String email, @RequestBody String password){
        return "login";
    }


}
