package com.geterdone.get_er_done.dto.task;

public class GroupNameRequest {
    private String groupName;

    public GroupNameRequest() {}

    public GroupNameRequest(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
