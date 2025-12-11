package com.geterdone.get_er_done.model;

public class GroupProfile {
    private String groupID;
    private String groupName;

    GroupProfile() {}

    public GroupProfile(String groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
    }

    public String getGroupID() {
        return groupID;
    }
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
