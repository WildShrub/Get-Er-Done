package com.geterdone.get_er_done.model;

public class GroupSchedule {
    private String groupID;
    private String groupScheduleJson;

    public GroupSchedule(String groupID, String groupScheduleJson) {
        this.groupID = groupID;
        this.groupScheduleJson = groupScheduleJson; 
    }

    public String getGroupID() {
        return groupID;
    }
    public void setGroupID(String newGroupID) {
        groupID = newGroupID;
    }

    public String getGroupScheduleJson() {
        return groupScheduleJson;
    }

    public void setGroupScheduleJson(String newGroupScheduleJson) {
        groupScheduleJson = newGroupScheduleJson;
    }
}
