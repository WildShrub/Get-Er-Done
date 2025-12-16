package com.geterdone.get_er_done.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geterdone.get_er_done.model.GroupSchedule;
import com.geterdone.get_er_done.repository.GroupScheduleRepository;

@ExtendWith(MockitoExtension.class)
class GroupScheduleServiceTest {

    @Mock
    private GroupScheduleRepository repository;

    @InjectMocks
    private GroupScheduleService service;

    /* ---------------- getGroupSchedule ---------------- */

    @Test
    void getGroupSchedule_returnsScheduleFromRepository() {
        GroupSchedule schedule = new GroupSchedule();
        schedule.setGroupID("group1");

        when(repository.findByGroupID("group1")).thenReturn(schedule);

        GroupSchedule result = service.getGroupSchedule("group1");

        assertEquals("group1", result.getGroupID());
        verify(repository).findByGroupID("group1");
    }

    /* ---------------- saveGroupSchedule ---------------- */

    @Test
    void saveGroupSchedule_delegatesToRepository() {
        GroupSchedule schedule = new GroupSchedule();

        service.saveGroupSchedule(schedule);

        verify(repository).save(schedule);
    }

    /* ---------------- createGroupSchedule ---------------- */

    @Test
    void createGroupSchedule_createsAndSavesNewSchedule() {
        service.createGroupSchedule("group1");

        verify(repository).save(argThat(saved ->
            "group1".equals(saved.getGroupID())
        ));
    }
}
