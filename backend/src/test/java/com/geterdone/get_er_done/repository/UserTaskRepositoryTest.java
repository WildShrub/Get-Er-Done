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

import com.geterdone.get_er_done.model.UserTask;

@ExtendWith(MockitoExtension.class)
class UserTaskRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserTaskRepository repository;

    private UserTask sampleTask;

    @BeforeEach
    void setUp() {
        sampleTask = new UserTask("alice", "[{\"taskID\":\"1\"}]");
    }

    //findByUsername      
    @Test
    void findByUsername_userExists_returnsUserTask() {
        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq("alice")
        )).thenReturn(sampleTask);

        UserTask result = repository.findByUsername("alice");

        assertNotNull(result);
        assertEquals("alice", result.getUsername());
        assertEquals(sampleTask.getTasksJson(), result.getTasksJson());

        verify(jdbcTemplate).queryForObject(
                anyString(),
                any(RowMapper.class),
                eq("alice")
        );
    }

    @Test
    void findByUsername_userDoesNotExist_returnsNull() {
        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq("bob")
        )).thenThrow(new EmptyResultDataAccessException(1));

        UserTask result = repository.findByUsername("bob");

        assertNull(result);
    }


    //save
    @Test
    void save_insertsOrUpdatesUserTask() {
        when(jdbcTemplate.update(
                anyString(),
                eq("alice"),
                eq(sampleTask.getTasksJson())
        )).thenReturn(1);

        repository.save(sampleTask);

        verify(jdbcTemplate).update(
                anyString(),
                eq("alice"),
                eq(sampleTask.getTasksJson())
        );
    }
}