package com.geterdone.get_er_done.model;

import java.util.List;

public class GroupScheduleItem {
    private String scheduleID;
    private String name;
    private Boolean notification;
    private String startTime;
    private String endTime;
    private List<String> assignedUsers;

    public GroupScheduleItem() {
    }

    public GroupScheduleItem(String scheduleID, String name, Boolean notification, String startTime, String endTime, List<String> assignedUsers) {
        this.scheduleID = scheduleID;
        this.name = name;
        this.notification = notification;
        this.startTime = startTime;
        this.endTime = endTime;
        this.assignedUsers = assignedUsers;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public String getName() {
        return name;
    }

    public Boolean getNotification() {
        return notification;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<String> getAssignedUsers() {
        return assignedUsers;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNotification(Boolean notification) {
        this.notification = notification;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public void setAssignedUsers(List<String> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }
}