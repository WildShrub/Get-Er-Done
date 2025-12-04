package com.geterdone.get_er_done.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/schedule")
public class UserScheduleController {

    private final UserScheduleService service;

    public UserScheduleController(UserScheduleService service) {
        service = service;
    }

    @GetMapping("/get/{username}")
    public UserSchedule getSchedule(@PathVariable String username) {
        return service.getUserSchedule(username);
    }

    @PostMapping("/create")
    public void createSchedule(@RequestBody UserSchedule schedule) {
        service.createUserSchedule(schedule);
    }
}