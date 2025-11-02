package com.geterdone.get_er_done.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

    @GetMapping("/hello")
    public String hello() {
        return "Get Er Done API is running!";
    }

    @PostMapping("/message")
    public String receiveMessage(@RequestBody String message) {
        return "Server received: " + message;
    }
}
