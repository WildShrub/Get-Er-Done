package com.geterdone.get_er_done.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import com.geterdone.get_er_done.model.UserSchedule;

@ExtendWith(MockitoExtension.class)
public class UserScheduleRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserScheduleRepository repository;

    private UserSchedule sampleSchedule;

    @BeforeEach
    void setUp() {
        sampleSchedule = new UserSchedule("alice", "[{\"day\":\"Monday\"}]");
    }

    //findByUsername
    @Test
    void findByUsername_userExists_returnsUserSchedule() {
        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq("alice")
        )).thenReturn(sampleSchedule);

        UserSchedule result = repository.findByUsername("alice");

        assertNotNull(result);
        assertEquals("alice", result.getUsername());
        assertEquals(sampleSchedule.getUserScheduleJson(), result.getUserScheduleJson());

        verify(jdbcTemplate).queryForObject(
                anyString(),
                any(RowMapper.class),
                eq("alice")
        );
    }

    //save
    @Test
    void save_insertsOrUpdatesUserSchedule() {
        when(jdbcTemplate.update(
                anyString(),
                anyString(),
                anyString()
        )).thenReturn(1);

        repository.save(sampleSchedule);

        verify(jdbcTemplate).update(
                anyString(),
                eq(sampleSchedule.getUsername()),
                eq(sampleSchedule.getUserScheduleJson())
        );
    }

}