package com.geterdone.get_er_done.service;

import org.springframework.stereotype.Service;

import com.geterdone.get_er_done.model.UserTask;
import com.geterdone.get_er_done.repository.UserTaskRepository;

@Service
public class UserTaskService {

    private final UserTaskRepository repository;

    public UserTaskService(UserTaskRepository repository) {
        this.repository = repository;
    }

    public UserTask getUserTasks(String username) {
        return repository.findByUsername(username);
    }

    public void saveUserTasks(UserTask tasks) {
        repository.save(tasks);
    }
}