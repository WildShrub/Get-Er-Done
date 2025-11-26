package com.geterdone.get_er_done.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class UserScheduleController {

    private final UserScheduleService service;

    public UserScheduleController(UserScheduleService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public UserSchedule getSchedule(@PathVariable String username) {
        return service.getUserSchedule(username);
    }

    @PostMapping
    public void createSchedule(@RequestBody UserSchedule schedule) {
        service.createUserSchedule(schedule);
    }
}