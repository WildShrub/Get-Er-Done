package com.geterdone.get_er_done.model;

public class UserSchedule {

    private String username;
    private String userSchedule;

    public UserSchedule() {}

    public UserSchedule(String username, String userSchedule) {
        this.username = username;
        this.userSchedule = userSchedule;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserSchedule() {
        return userSchedule;
    }

    public void setUserSchedule(String userSchedule) {
        this.userSchedule = userSchedule;
    }
}