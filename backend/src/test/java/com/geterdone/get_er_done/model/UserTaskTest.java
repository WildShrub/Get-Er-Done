package com.geterdone.get_er_done.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class UserTaskTest {

    @Test
    void constructor_setsFieldsCorrectly() {
        UserTask task = new UserTask("alice", "[]");

        assertEquals("alice", task.getUsername());
        assertEquals("[]", task.getTasksJson());
    }

    @Test
    void setters_updateFieldsCorrectly() {
        UserTask task = new UserTask();

        task.setUsername("bob");
        task.setTasksJson("[{\"name\":\"Buy milk\"}]");

        assertEquals("bob", task.getUsername());
        assertEquals("[{\"name\":\"Buy milk\"}]", task.getTasksJson());
    }
}

