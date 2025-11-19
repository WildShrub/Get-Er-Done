package com.geterdone.get_er_done.Group;
// ===== SAMPLE FILE: ExampleUser.java =====
// This is ONLY an example entity to show how JPA works.
// You can delete or rename this for your real project.

package com.geterdone.get_er_done.Group;

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

    public ExampleUser() {
        // No-arg constructor required by JPA
    }

    public Task(String GroupName, Boolean completeGroupTask) {
        this.GroupName = GroupName;
        this.completeGroupTask = completeGroupTask;
    }

    //Getters and Setters:

    public Long getTaskId() {
        return id;
    }

    public void setTaskId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public Boolean completeGroupTask() {
        return completeGroupTask;
    }

    public int getPriorityTask() {
        return Priority();
    }

    public void setPriorityTask(int Priority) {
        this.Priority = Priority;
    }

    public int getDueDateTask() {
        return DueDate();
    }

    public void setDueDateTask(int DueDate) {
        this.DueDate = DueDate;
    }

    public int parentTaskID() {
        return ParentTaskID();
    }

    public void setParentTaskID(int ParentTaskID) {
        this.ParentTaskID = ParentTaskID;
    }
}