package com.geterdone.get_er_done.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
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
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
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
            if (userTasks == null || userTasks.getTasksJson() == null || userTasks.getTasksJson().isEmpty()) {
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

            String jsonOut = mapper.writeValueAsString(taskList);

            userTasks.setTasksJson(jsonOut);
            repository.save(userTasks);

        } catch (Exception e) {
            throw new RuntimeException("Failed to add task", e);
        }
    }
}
