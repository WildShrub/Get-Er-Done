package com.geterdone.get_er_done.model;

public class UserLogin {

    private String username;
    private String password;

    public UserLogin() {}

    public UserLogin(String username, String password) {        //maybe should be private?
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {       //this will be returning a password hash
        return password;
    }

    public void setPassword(String password) {  //this needs to hash the password that is sent to the system.
        this.password = password;
    }
}