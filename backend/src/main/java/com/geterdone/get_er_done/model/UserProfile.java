package com.geterdone.get_er_done.model;

public class UserProfile {

    private String username;
    private String email;
    private String pfp; // Profile picture URL or base64 string

    public UserProfile() {}

    public UserProfile(String username, String email, String pfp) {
        this.username = username;
        this.email = email;
        this.pfp = pfp;
    }

    public String getUsername() { 
        return username; 
    }
    public void setUsername(String username) { 
        this.username = username; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email;
    }

    public String getPfp() {
        return pfp;
    }
    public void setPfp(String pfp) {
        this.pfp = pfp;
    }
}