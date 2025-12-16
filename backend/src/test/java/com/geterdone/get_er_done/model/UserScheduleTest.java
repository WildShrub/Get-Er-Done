package com.geterdone.get_er_done.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserScheduleTest {

    @Test
    void constructor_setsFieldsCorrectly() {
        UserSchedule schedule = new UserSchedule("alice", "{}");

        assertEquals("alice", schedule.getUsername());
        assertEquals("{}", schedule.getUserScheduleJson());
    }

    @Test
    void setters_updateFieldsCorrectly() {
        UserSchedule schedule = new UserSchedule();

        schedule.setUsername("bob");
        schedule.setUserScheduleJson("{\"monday\":\"9am\"}");

        assertEquals("bob", schedule.getUsername());
        assertEquals("{\"monday\":\"9am\"}", schedule.getUserScheduleJson());
    }
}
