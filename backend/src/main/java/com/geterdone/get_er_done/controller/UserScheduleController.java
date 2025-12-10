package com.geterdone.get_er_done.controller;

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

    private final UserScheduleService service;

    public UserScheduleController(UserScheduleService service) {
        this.service = service;
    }

    // Fetch user schedule
    @GetMapping("/{username}")
    public UserSchedule getSchedule(@PathVariable String username) {
        return service.getUserSchedule(username);
    }

    // Create/update schedule
    @PostMapping("/save")
    public void saveSchedule(@RequestBody UserSchedule schedule) {
        service.saveUserSchedule(schedule);
    }
}