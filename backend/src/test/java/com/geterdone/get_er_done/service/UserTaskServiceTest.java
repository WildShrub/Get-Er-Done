package com.geterdone.get_er_done.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.UserTask;
import com.geterdone.get_er_done.model.UserTaskItem;
import com.geterdone.get_er_done.repository.UserTaskRepository;

@ExtendWith(MockitoExtension.class)
class UserTaskServiceTest {

    @Mock
    private UserTaskRepository repository;

    private UserTaskService service;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper(); // actual mapper
        service = new UserTaskService(repository, objectMapper);
    }

    @Test
    void getUserTasks_returnsUserTasks() {
        UserTask tasks = new UserTask();
        tasks.setUsername("alice");

        when(repository.findByUsername("alice")).thenReturn(tasks);

        UserTask result = service.getUserTasks("alice");

        assertNotNull(result);
        assertEquals("alice", result.getUsername());
    }

    @Test
    void addTask_createsNewUserAndAddsTask() throws Exception {
        when(repository.findByUsername("alice")).thenReturn(null);

        UserTaskItem task = new UserTaskItem();
        task.setName("Buy milk");
        task.setPriority(1);

        service.addTask("alice", task);

        verify(repository).save(argThat(saved -> {
            try {
                List<UserTaskItem> items = objectMapper.readValue(
                    saved.getTasksJson(),
                    objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, UserTaskItem.class)
                );
                return items.size() == 1 &&
                       items.get(0).getName().equals("Buy milk") &&
                       items.get(0).getTaskID() != null;
            } catch (Exception e) {
                return false;
            }
        }));
    }

    @Test
    void changeCompletionStatus_updatesTask() throws Exception {
        UserTaskItem task = new UserTaskItem();
        task.setTaskID("t1");
        task.setCompleted(false);

        UserTask userTasks = new UserTask();
        userTasks.setUsername("alice");
        userTasks.setTasksJson(objectMapper.writeValueAsString(List.of(task)));

        when(repository.findByUsername("alice")).thenReturn(userTasks);

        service.changeCompletionStatus("alice", "t1", true);

        verify(repository).save(argThat(saved -> {
            try {
                List<UserTaskItem> items = objectMapper.readValue(
                    saved.getTasksJson(),
                    objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, UserTaskItem.class)
                );
                return items.get(0).isCompleted();
            } catch (Exception e) {
                return false;
            }
        }));
    }

    @Test
    void sortByPriority_sortsDescending() throws Exception {
        UserTaskItem low = new UserTaskItem();
        low.setPriority(1);

        UserTaskItem high = new UserTaskItem();
        high.setPriority(5);

        UserTask userTasks = new UserTask();
        userTasks.setUsername("alice");
        userTasks.setTasksJson(objectMapper.writeValueAsString(List.of(low, high)));

        when(repository.findByUsername("alice")).thenReturn(userTasks);

        service.sortByPriority("alice");

        verify(repository).save(argThat(saved -> {
            try {
                List<UserTaskItem> items = objectMapper.readValue(
                    saved.getTasksJson(),
                    objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, UserTaskItem.class)
                );
                return items.get(0).getPriority() == 5;
            } catch (Exception e) {
                return false;
            }
        }));
    }

    @Test
    void editDescription_updatesDescription() throws Exception {
        UserTaskItem task = new UserTaskItem();
        task.setTaskID("t1");
        task.setDescription("old");

        UserTask userTasks = new UserTask();
        userTasks.setUsername("alice");
        userTasks.setTasksJson(objectMapper.writeValueAsString(List.of(task)));

        when(repository.findByUsername("alice")).thenReturn(userTasks);

        service.editDescription("alice", "t1", "new");

        verify(repository).save(argThat(saved -> {
            try {
                List<UserTaskItem> items = objectMapper.readValue(
                    saved.getTasksJson(),
                    objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, UserTaskItem.class)
                );
                return items.get(0).getDescription().equals("new");
            } catch (Exception e) {
                return false;
            }
        }));
    }
}