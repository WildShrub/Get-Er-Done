package com.geterdone.get_er_done.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.model.UserSchedule;
import com.geterdone.get_er_done.service.UserScheduleService;

@RestController
@RequestMapping("/api/user/schedule")
public class UserScheduleController {

    @Autowired
    private UserScheduleService service;

    @GetMapping("/{username}")
    public UserSchedule getSchedule(@PathVariable String username) {
        return service.getSchedule(username);
    }

    @PostMapping("/{username}")
    public void updateSchedule(@PathVariable String username, @RequestBody String json) {
        service.updateSchedule(username, json);
    }
}