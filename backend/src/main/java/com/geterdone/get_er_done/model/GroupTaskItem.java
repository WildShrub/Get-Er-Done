package com.geterdone.get_er_done.model;

public class GroupTaskItem {
    private String taskID;
    private String name;
    private Boolean completed;
    private String dueDate;
    private java.util.List<String> assignedTo;
    private String priority;

    public GroupTaskItem() {}

    public String getTaskID() {
        return taskID;
    }
    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Boolean isCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getDueDate() {
        return dueDate;
    }
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public java.util.List<String> getAssignedTo() {
        return assignedTo;
    }
    public void setAssignedTo(java.util.List<String> assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
}
