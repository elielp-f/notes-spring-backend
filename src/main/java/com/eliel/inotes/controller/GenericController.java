package com.eliel.inotes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class GenericController {
    @GetMapping("/hello")
    public String SayHello(){
        return "I'm the SUPERMAN";
    }
}
