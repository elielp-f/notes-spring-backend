package com.notes.notes.controllers;

import com.notes.notes.modals.User;
import com.notes.notes.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid User user) throws Exception {
        User userSaved = this.userServices.createUser(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getHash()
        );
        return String.format("Welcome %s %s to THE NOTES your id is %s", userSaved.getFirstName(), userSaved.getLastName(), userSaved.getId());
    }

    @PostMapping("/user/edit")
    public User editUser(@RequestBody @Valid User user) throws Exception {
         return this.userServices.editUser(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getHash(),
                user.getId()
        );
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id){
        this.userServices.deleteUser(id);
    }


    @PostMapping("/login")
    public String login(@RequestBody String email, @RequestBody String password){
        return "login";
    }


}
