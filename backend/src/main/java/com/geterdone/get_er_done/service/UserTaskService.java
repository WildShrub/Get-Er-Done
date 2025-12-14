package com.geterdone.get_er_done.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.UserTask;
import com.geterdone.get_er_done.model.UserTaskItem;
import com.geterdone.get_er_done.repository.UserTaskRepository;

@Service
public class UserTaskService {

    private final UserTaskRepository repository;
    private final ObjectMapper mapper;

    public UserTaskService(UserTaskRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserTask getUserTasks(String username) {
        return repository.findByUsername(username);
    }

    public void saveUserTasks(UserTask tasks) {
        repository.save(tasks);
    }

    public void addTask(String username, UserTaskItem task) {
        try {
            UserTask userTasks = repository.findByUsername(username);
            List<UserTaskItem> taskList;

            if (userTasks == null || userTasks.getTasksJson() == null) {
                taskList = new ArrayList<>();
                userTasks = new UserTask();
                userTasks.setUsername(username);
            } else {
                taskList = mapper.readValue(
                    userTasks.getTasksJson(),
                    new TypeReference<List<UserTaskItem>>() {}
                );
            }

            task.setTaskID(UUID.randomUUID().toString());
            taskList.add(task);

            userTasks.setTasksJson(mapper.writeValueAsString(taskList));
            repository.save(userTasks);

        } catch (Exception e) {
            throw new RuntimeException("Failed to add task", e);
        }
    }
}
