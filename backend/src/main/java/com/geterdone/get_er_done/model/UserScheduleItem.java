package com.geterdone.get_er_done.model;

public class UserScheduleItem {
    private String scheduleID;
    private String name;
    private Boolean notification;
    private String startTime;
    private String endTime;

    public UserScheduleItem() {
    }

    public UserScheduleItem(String scheduleID, String name, Boolean notification, String startTime, String endTime) {
        this.scheduleID = scheduleID;
        this.name = name;
        this.notification = notification;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getScheduleID() {
        return scheduleID;
    }
    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Boolean getNotification() {
        return notification;
    }   
    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
