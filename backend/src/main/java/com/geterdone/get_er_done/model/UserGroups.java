package com.geterdone.get_er_done.model;

import java.util.List;

public class UserGroups {
    private String username;
    private List<String> groupsID;

    public UserGroups() {}
    public UserGroups(String username, List<String> groupsID) {
        this.username = username;
        this.groupsID = groupsID;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public List<String> getGroupsID() {
        return groupsID;
    }
    public void setGroupsID(List<String> groupsID) {
        this.groupsID = groupsID;
    }
}