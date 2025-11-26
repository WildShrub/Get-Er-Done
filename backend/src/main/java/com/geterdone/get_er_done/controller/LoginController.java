package com.geterdone.get_er_done.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class UserLoginController {

    private final UserLoginService service;

    public UserLoginController(UserLoginService service) {
        this.service = service;
    }

    @PostMapping("/{username}")
    public UserLogin getLogin(@PathVariable String username) {
        return service.getUserLogin(username);
    }

    @PostMapping("/{username}")
    public void createLogin(@RequestBody UserLogin login) {
        service.createUserLogin(login);
    }
}