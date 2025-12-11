package com.geterdone.get_er_done.model;

public class GroupTask {
    private String groupID;
    private String tasksJson; // This holds the JSON for tasks

    public GroupTask() {}

    public GroupTask(String groupID, String tasksJson) {
        this.groupID = groupID;
        this.tasksJson = tasksJson;
    }

    public String getGroupID() {
        return groupID;
    }
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }
    public String getTasksJson() {
        return tasksJson;
    }
    public void setTasksJson(String tasksJson) {
        this.tasksJson = tasksJson;
    }
}
