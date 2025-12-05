package com.geterdone.get_er_done.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.model.UserLogin;
import com.geterdone.get_er_done.service.UserLoginService;

@RestController
@RequestMapping("/api/login")
public class UserLoginController {

    private final UserLoginService service;

    public UserLoginController(UserLoginService service) {
        this.service = service;
    }

    // Create a new user
    @PostMapping("/register")
    public void registerUser(@RequestBody UserLogin login) {
        service.registerUser(login);
    }

    // Validate login
    @PostMapping("/authenticate")
    public boolean authenticate(
            @RequestParam String username,
            @RequestParam String password) {
        return service.validateLogin(username, password);
    }

    // Retrieve a user (for debugging)
    @GetMapping("/{username}")
    public UserLogin getUser(@PathVariable String username) {
        return service.getUserLogin(username);
    }
}