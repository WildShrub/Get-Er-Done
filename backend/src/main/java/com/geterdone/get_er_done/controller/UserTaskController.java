package com.geterdone.get_er_done.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.model.UserTask;
import com.geterdone.get_er_done.service.UserTaskService;

@RestController
@RequestMapping("/api/user/tasks")
public class UserTaskController {

    private final UserTaskService service;

    public UserTaskController(UserTaskService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public UserTask getTasks(@PathVariable String username) {
        return service.getUserTasks(username);
    }

    @PostMapping("/save")
    public void saveTasks(@RequestBody UserTask tasks) {
        service.saveUserTasks(tasks);
    }
}