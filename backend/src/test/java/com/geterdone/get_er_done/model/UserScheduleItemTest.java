package com.geterdone.get_er_done.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserScheduleItemTest {

    @Test
    void constructor_andSetters_workCorrectly() {
        UserScheduleItem item = new UserScheduleItem(
            "sched-1",
            "Team Meeting",
            true,
            "09:00",
            "10:00"
        );

        assertEquals("sched-1", item.getScheduleID());
        assertEquals("Team Meeting", item.getName());
        assertEquals(true, item.getNotification());
        assertEquals("09:00", item.getStartTime());
        assertEquals("10:00", item.getEndTime());
    }
}
