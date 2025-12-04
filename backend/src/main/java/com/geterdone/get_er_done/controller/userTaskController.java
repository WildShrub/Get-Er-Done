package com.geterdone.get_er_done.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/task")
public class UserTaskController {

    private final UserTaskService service;

    public UserTaskController(UserTaskService service) {
        this.service = service;
    }

    @GetMapping("/get/{username}")
    public UserTask getTask(@PathVariable String username) {
        return service.getUserTask(username);
    }

    @PostMapping("/create")
    public void createTask(@RequestBody UserTask task) {
        service.createUserTask(task);
    }
}