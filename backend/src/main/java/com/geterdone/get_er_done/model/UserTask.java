package com.geterdone.get_er_done.model;

public class UserTask {

    private String username;
    private String tasksJson; // This holds the JSON for tasks

    public UserTask() {}

    public UserTask(String username, String tasksJson) {
        this.username = username;
        this.tasksJson = tasksJson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTasksJson() {
        return tasksJson;
    }

    public void setTasksJson(String tasksJson) {
        this.tasksJson = tasksJson;
    }
}