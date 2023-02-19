package com.notes.notes;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/")
    public String helloworld(){
        return "hello world";
    }
    @PostMapping("/signup")
    public String signup(@RequestBody @Valid User user){
        return String.format("Welcome %s %s to THE NOTES", user.getFirstName(), user.getLastName());
    }

    @PostMapping("/login")
    public String login(@RequestBody String email, @RequestBody String password){
        return "login";
    }


}
