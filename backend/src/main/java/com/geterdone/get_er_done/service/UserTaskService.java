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
            System.out.println("UserTaskService: Adding task for user: " + username);                                     //for testing 
            System.out.println("UserTaskService: Retrieving existing tasks...");                                             //for testing   
            UserTask userTasks = repository.findByUsername(username);

            List<UserTaskItem> taskList = new ArrayList<>();
            if (userTasks == null || userTasks.getTasksJson() == null || userTasks.getTasksJson().isEmpty()) {
                System.out.println("UserTaskService: No existing tasks found, initializing new task list.");                          //for testing
                userTasks = new UserTask();
                userTasks.setUsername(username);
            } else {
                System.out.println("UserTaskService: Existing tasks found, parsing tasks JSON.");                                      //for testing

                //taskList = new ArrayList<>();
                taskList = mapper.readValue(
                    userTasks.getTasksJson(),
                    new TypeReference<List<UserTaskItem>>() {}
                );
                System.out.println("UserTaskService: Tasks JSON parsed successfully.");                                               //for testing
            }

            task.setTaskID(UUID.randomUUID().toString());

            taskList.add(task);

            String jsonOut = mapper.writeValueAsString(taskList);

            userTasks.setTasksJson(jsonOut);
            repository.save(userTasks);

        } catch (Exception e) {
            System.out.println("UserTaskService: Failed to add task for user: " + username);                                               //for testing
            throw new RuntimeException("Failed to add task", e);
        }
    }

    public void changeCompletionStatus(String username, String taskID, boolean completed) {
        try {
            UserTask userTasks = repository.findByUsername(username);
            if (userTasks == null || userTasks.getTasksJson() == null || userTasks.getTasksJson().isEmpty()) {
                throw new RuntimeException("No tasks found for user: " + username);
            }

            List<UserTaskItem> taskList = mapper.readValue(
                userTasks.getTasksJson(),
                new TypeReference<List<UserTaskItem>>() {}
            );

            for (UserTaskItem task : taskList) {
                if (task.getTaskID().equals(taskID)) {
                    task.setCompleted(completed);
                    break;
                }
            }

            String jsonOut = mapper.writeValueAsString(taskList);
            userTasks.setTasksJson(jsonOut);
            repository.save(userTasks);

        } catch (Exception e) {
            throw new RuntimeException("Failed to change completion status", e);
        }
    }

    public void sortByPriority(String username) {
        try {
            UserTask userTasks = repository.findByUsername(username);
            if (userTasks == null || userTasks.getTasksJson() == null || userTasks.getTasksJson().isEmpty()) {
                throw new RuntimeException("No tasks found for user: " + username);
            }

            List<UserTaskItem> taskList = mapper.readValue(
                userTasks.getTasksJson(),
                new TypeReference<List<UserTaskItem>>() {}
            );

            taskList.sort((a, b) -> Integer.compare(b.getPriority(), a.getPriority()));

            String jsonOut = mapper.writeValueAsString(taskList);
            userTasks.setTasksJson(jsonOut);
            repository.save(userTasks);

        } catch (Exception e) {
            throw new RuntimeException("Failed to sort tasks by priority", e);
        }
    }

    public void editDescription(String username, String taskID, String newDescription) {
        try {
            UserTask userTasks = repository.findByUsername(username);
            if (userTasks == null || userTasks.getTasksJson() == null || userTasks.getTasksJson().isEmpty()) {
                throw new RuntimeException("No tasks found for user: " + username);
            }

            List<UserTaskItem> taskList = mapper.readValue(
                userTasks.getTasksJson(),
                new TypeReference<List<UserTaskItem>>() {}
            );

            for (UserTaskItem task : taskList) {
                if (task.getTaskID().equals(taskID)) {
                    task.setDescription(newDescription);
                    break;
                }
            }

            String jsonOut = mapper.writeValueAsString(taskList);
            userTasks.setTasksJson(jsonOut);
            repository.save(userTasks);

        } catch (Exception e) {
            throw new RuntimeException("Failed to edit task description", e);
        }
    }
}
