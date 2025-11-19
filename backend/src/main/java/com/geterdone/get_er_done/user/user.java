// ===== SAMPLE FILE: User.java =====
// This is ONLY an example entity to show how JPA works.
// You can delete or rename this for your real project.

package com.geterdone.get_er_done.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "User")
public class user {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private String userName;
    
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String pfp;

    @Column(nullable = false)
    private String taskList;

    @Column(nullable = false)
    private String schedule;

    @Column(nullable = false)
    private String groupList;

    //Constructers:

    public user() {
        // No-arg constructor required by JPA
    }

    public user(String userName, String password, String pfp, String taskList, String schedule, String groupList) {
        this.userName = userName;
        this.password = password;
        this.pfp = pfp;
        this.taskList = taskList;
        this.schedule = schedule;
        this.groupList = groupList;
    }

    //Getters and Setters:

    public String getuserName() {
        return userName;
    }
    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getpassword() {
        return password;
    }
    public void setpassword(String password) {
        this.password = password;
    }

    public String getpfp() {
        return pfp;
    }
    public void setpfp(String pfp) {
        this.pfp = pfp;
    }

    public String gettaskList() {
        return taskList;
    }
    public void settaskList(String taskList) {
        this.taskList = taskList;
    }

    public String getschedule() {
        return schedule;
    }
    public void setschedule(String schedule) {
        this.schedule = schedule;
    }

    public String getgroupList() {
        return groupList;
    }
    public void setgroupList(String groupList) {
        this.groupList = groupList;
    }
}