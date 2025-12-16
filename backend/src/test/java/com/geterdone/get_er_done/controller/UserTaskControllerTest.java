package com.geterdone.get_er_done.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.dto.task.CompletionRequest;
import com.geterdone.get_er_done.dto.task.DescriptionRequest;
import com.geterdone.get_er_done.model.UserTask;
import com.geterdone.get_er_done.model.UserTaskItem;
import com.geterdone.get_er_done.service.UserTaskService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserTaskController.class)
class UserTaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserTaskService service;

    @Autowired
    private ObjectMapper objectMapper;

    /* ---------------- GET /{username} ---------------- */

    @Test
    void getTasks_returns200_whenUserExists() throws Exception {
        UserTask mockTask = new UserTask();
        mockTask.setUsername("alice");
        mockTask.setTasksJson("[]");

        Mockito.when(service.getUserTasks("alice"))
               .thenReturn(mockTask);

        mockMvc.perform(get("/api/user/tasks/alice"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username").value("alice"));
    }

    @Test
    void getTasks_returns404_whenUserNotFound() throws Exception {
        Mockito.when(service.getUserTasks("missing"))
               .thenReturn(null);

        mockMvc.perform(get("/api/user/tasks/missing"))
               .andExpect(status().isNotFound());
    }

    /* ---------------- POST /{username}/add ---------------- */

    @Test
    void addTask_returns200() throws Exception {
        UserTaskItem task = new UserTaskItem();
        task.setName("Buy milk");
        task.setDescription("2 liters");
        task.setCompleted(false);
        task.setPriority(1);

        mockMvc.perform(post("/api/user/tasks/alice/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
               .andExpect(status().isOk());

        Mockito.verify(service)
               .addTask(Mockito.eq("alice"), Mockito.any(UserTaskItem.class));
    }

    /* ---------------- PATCH /completion ---------------- */

    @Test
    void updateCompletionStatus_returns200() throws Exception {
        CompletionRequest request = new CompletionRequest(true);

        mockMvc.perform(patch("/api/user/tasks/alice/task123/completion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk());

        Mockito.verify(service)
               .changeCompletionStatus("alice", "task123", true);
    }

    /* ---------------- PATCH /description ---------------- */

    @Test
    void updateDescription_returns200() throws Exception {
        DescriptionRequest request = new DescriptionRequest("New description");

        mockMvc.perform(patch("/api/user/tasks/alice/task123/description")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk());

        Mockito.verify(service)
               .editDescription("alice", "task123", "New description");
    }

    /* ---------------- POST /sort/priority ---------------- */

    @Test
    void sortByPriority_returns200() throws Exception {
        mockMvc.perform(post("/api/user/tasks/alice/sort/priority"))
               .andExpect(status().isOk());

        Mockito.verify(service)
               .sortByPriority("alice");
    }
}