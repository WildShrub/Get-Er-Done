package com.geterdone.get_er_done.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTaskItemTest {

    @Test
    void settersAndGetters_workCorrectly() {
        UserTaskItem item = new UserTaskItem();

        item.setTaskID("task-123");
        item.setName("Buy milk");
        item.setDescription("2 liters of whole milk");
        item.setCompleted(true);
        item.setPriority(1);
        item.setDueDate("2025-12-20");

        assertEquals("task-123", item.getTaskID());
        assertEquals("Buy milk", item.getName());
        assertEquals("2 liters of whole milk", item.getDescription());
        assertTrue(item.isCompleted());
        assertEquals(1, item.getPriority());
        assertEquals("2025-12-20", item.getDueDate());
    }
}


