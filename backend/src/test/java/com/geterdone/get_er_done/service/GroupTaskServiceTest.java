package com.geterdone.get_er_done.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geterdone.get_er_done.model.GroupTask;
import com.geterdone.get_er_done.model.GroupTaskItem;
import com.geterdone.get_er_done.repository.GroupTaskRepository;

@ExtendWith(MockitoExtension.class)
class GroupTaskServiceTest {

    @Mock
    private GroupTaskRepository repository;

    @InjectMocks
    private GroupTaskService service;

    private final ObjectMapper mapper = new ObjectMapper();

    /* ---------------- getGroupTasks ---------------- */

    @Test
    void getGroupTasks_returnsTasks() {
        GroupTask groupTask = new GroupTask();
        groupTask.setGroupID("group1");

        when(repository.findByGroupID("group1")).thenReturn(groupTask);

        GroupTask result = service.getGroupTasks("group1");

        assertEquals("group1", result.getGroupID());
        verify(repository).findByGroupID("group1");
    }

    /* ---------------- saveGroupTasks ---------------- */

    @Test
    void saveGroupTasks_delegatesToRepository() {
        GroupTask tasks = new GroupTask();

        service.saveGroupTasks(tasks);

        verify(repository).save(tasks);
    }

    /* ---------------- addTask ---------------- */

    @Test
    void addTask_createsNewTaskList_whenNoneExists() throws Exception {
        when(repository.findByGroupID("group1")).thenReturn(null);

        GroupTaskItem task = new GroupTaskItem();
        task.setName("Test Task");
        task.setPriority("1");

        service.addTask("group1", task);

        verify(repository).save(argThat(saved -> {
            try {
                List<GroupTaskItem> list =
                    mapper.readValue(saved.getTasksJson(),
                        mapper.getTypeFactory().constructCollectionType(List.class, GroupTaskItem.class));

                return list.size() == 1 &&
                       list.get(0).getName().equals("Test Task") &&
                       list.get(0).getTaskID() != null;
            } catch (Exception e) {
                return false;
            }
        }));
    }

    /* ---------------- changeCompletionStatus ---------------- */

    @Test
    void changeCompletionStatus_updatesCorrectTask() throws Exception {
        GroupTaskItem item = new GroupTaskItem();
        item.setTaskID("task1");
        item.setCompleted(false);

        GroupTask groupTask = new GroupTask();
        groupTask.setGroupID("group1");
        groupTask.setTasksJson(mapper.writeValueAsString(List.of(item)));

        when(repository.findByGroupID("group1")).thenReturn(groupTask);

        service.changeCompletionStatus("group1", "task1", true);

        verify(repository).save(argThat(saved -> {
            try {
                List<GroupTaskItem> list =
                    mapper.readValue(
                        saved.getTasksJson(),
                        new com.fasterxml.jackson.core.type.TypeReference<List<GroupTaskItem>>() {}
                    );

                GroupTaskItem updated = list.get(0);
                return updated.isCompleted();
            } catch (Exception e) {
                return false;
            }
    }));
}


    @Test
    void changeCompletionStatus_throwsWhenNoTasks() {
        when(repository.findByGroupID("group1")).thenReturn(null);

        assertThrows(RuntimeException.class,
            () -> service.changeCompletionStatus("group1", "task1", true));
    }

    /* ---------------- sortByPriority ---------------- */

    @Test
    void sortByPriority_sortsNumerically() throws Exception {
        GroupTaskItem high = new GroupTaskItem();
        high.setPriority("5");

        GroupTaskItem low = new GroupTaskItem();
        low.setPriority("1");

        GroupTask groupTask = new GroupTask();
        groupTask.setGroupID("group1");
        groupTask.setTasksJson(mapper.writeValueAsString(List.of(high, low)));

        when(repository.findByGroupID("group1")).thenReturn(groupTask);

        service.sortByPriority("group1");

        verify(repository).save(argThat(saved -> {
            try {
                List<GroupTaskItem> sorted =
                    mapper.readValue(saved.getTasksJson(),
                        mapper.getTypeFactory().constructCollectionType(List.class, GroupTaskItem.class));

                return sorted.get(0).getPriority().equals("1") &&
                       sorted.get(1).getPriority().equals("5");
            } catch (Exception e) {
                return false;
            }
        }));
    }

    @Test
    void sortByPriority_throwsWhenNoTasks() {
        when(repository.findByGroupID("group1")).thenReturn(null);

        assertThrows(RuntimeException.class,
            () -> service.sortByPriority("group1"));
    }
}
