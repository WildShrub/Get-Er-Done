package com.geterdone.get_er_done.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.GroupTask;
import com.geterdone.get_er_done.model.GroupTaskItem;
import com.geterdone.get_er_done.service.GroupTaskService;
import com.geterdone.get_er_done.dto.task.CompletionRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GroupTaskController.class)
class GroupTaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupTaskService service;

    @Autowired
    private ObjectMapper objectMapper;

    /* ---------------- GET /{groupID} ---------------- */

    @Test
    void getTasks_returnsGroupTask() throws Exception {
        GroupTask task = new GroupTask();
        task.setGroupID("group1");

        when(service.getGroupTasks("group1")).thenReturn(task);

        mockMvc.perform(get("/api/group/tasks/group1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.groupID").value("group1"));
    }

    /* ---------------- POST /save ---------------- */

    @Test
    void saveTasks_delegatesToService() throws Exception {
        GroupTask task = new GroupTask();
        task.setGroupID("group2");

        mockMvc.perform(post("/api/group/tasks/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
               .andExpect(status().isOk());

        verify(service).saveGroupTasks(any(GroupTask.class));
    }

    /* ---------------- POST /{groupID}/add ---------------- */

    @Test
    void addTask_delegatesToService() throws Exception {
        GroupTaskItem item = new GroupTaskItem();
        item.setName("Test Task");

        mockMvc.perform(post("/api/group/tasks/group3/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item)))
               .andExpect(status().isOk());

        verify(service).addTask(eq("group3"), any(GroupTaskItem.class));
    }

    /* ---------------- PATCH /{groupID}/{taskId}/completion ---------------- */

    @Test
    void updateCompletionStatus_delegatesToService() throws Exception {
        CompletionRequest request = new CompletionRequest(true);

        mockMvc.perform(patch("/api/group/tasks/group4/task123/completion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk());

        verify(service).changeCompletionStatus("group4", "task123", true);
    }

    /* ---------------- POST /{groupID}/sort/priority ---------------- */

    @Test
    void sortByPriority_delegatesToService() throws Exception {
        mockMvc.perform(post("/api/group/tasks/group5/sort/priority"))
               .andExpect(status().isOk());

        verify(service).sortByPriority("group5");
    }
}
