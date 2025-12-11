package com.geterdone.get_er_done.model;

public class UserGroups {
    private String username;
    private String groupIDListJson; // This holds the JSON for the list of group IDs

    public UserGroups() {}
    public UserGroups(String username, String groupIDListJson) {
        this.username = username;
        this.groupIDListJson = groupIDListJson;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getGroupIDListJson() {
        return groupIDListJson;
    }
    public void setGroupIDListJson(String groupIDListJson) {
        this.groupIDListJson = groupIDListJson;
    }
}