package com.geterdone.get_er_done.model;

public class UserSchedule {
    private String username;
    private String userScheduleJson;

    public UserSchedule() {}

    public UserSchedule(String username, String userScheduleJson) {
        this.username = username;
        this.userScheduleJson = userScheduleJson;
    }

    // getters & setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserScheduleJson() {
        return userScheduleJson;
    }
    public void setUserScheduleJson(String userScheduleJson) {
        this.userScheduleJson = userScheduleJson;
    }
}