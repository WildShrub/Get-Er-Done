package com.geterdone.get_er_done.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geterdone.get_er_done.model.UserSchedule;
import com.geterdone.get_er_done.repository.UserScheduleRepository;

@ExtendWith(MockitoExtension.class)
class UserScheduleServiceTest {

    @Mock
    private UserScheduleRepository repository;

    @InjectMocks
    private UserScheduleService service;

    /* ---------------- getUserSchedule ---------------- */

    @Test
    void getUserSchedule_returnsScheduleFromRepository() {
        UserSchedule schedule = new UserSchedule();
        schedule.setUsername("alice");

        when(repository.findByUsername("alice")).thenReturn(schedule);

        UserSchedule result = service.getUserSchedule("alice");

        assertEquals("alice", result.getUsername());
        verify(repository).findByUsername("alice");
    }

    /* ---------------- saveUserSchedule ---------------- */

    @Test
    void saveUserSchedule_delegatesToRepository() {
        UserSchedule schedule = new UserSchedule();

        service.saveUserSchedule(schedule);

        verify(repository).save(schedule);
    }

    /* ---------------- createUserSchedule ---------------- */

    @Test
    void createUserSchedule_createsAndSavesScheduleWithUsername() {
        ArgumentCaptor<UserSchedule> captor =
                ArgumentCaptor.forClass(UserSchedule.class);

        service.createUserSchedule("bob");

        verify(repository).save(captor.capture());

        UserSchedule saved = captor.getValue();
        assertEquals("bob", saved.getUsername());
    }
}
