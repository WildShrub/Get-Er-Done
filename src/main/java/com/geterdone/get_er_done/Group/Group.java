
package com.geterdone.get_er_done.Group.Group.java;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String GroupName;

    @Column(nullable = false, unique = true)
    private Boolean completeGroupTask;

    //Constructers:

    public Group() {
        // No-arg constructor required by JPA
    }

    public Group(String GroupName, Boolean completeGroupTask) {
        this.GroupName = GroupName;
        this.completeGroupTask = completeGroupTask;
    }

    //Getters and Setters:

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public Boolean completeGroupTask() {
        return completeGroupTask;
    }

    //Username List JSON

    //Group Task List JSON

    //Group Schedule JSON

}
