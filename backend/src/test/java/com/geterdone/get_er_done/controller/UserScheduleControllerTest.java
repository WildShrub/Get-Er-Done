package com.geterdone.get_er_done.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.UserSchedule;
import com.geterdone.get_er_done.service.UserScheduleService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserScheduleController.class)
class UserScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserScheduleService service;

    @Autowired
    private ObjectMapper objectMapper;

    /* ---------------- GET /{username} ---------------- */

    @Test
    void getSchedule_returns200_andSchedule() throws Exception {
        UserSchedule schedule = new UserSchedule(
            "alice",
            "{\"monday\":\"9am\"}"
        );

        when(service.getUserSchedule("alice")).thenReturn(schedule);

        mockMvc.perform(get("/api/user/schedule/alice"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username").value("alice"))
               .andExpect(jsonPath("$.userScheduleJson").value("{\"monday\":\"9am\"}"));
    }

    /* ---------------- POST /{username} ---------------- */

    @Test
    void saveSchedule_returns200_whenUsernamesMatch() throws Exception {
        UserSchedule schedule = new UserSchedule(
            "alice",
            "{\"tuesday\":\"10am\"}"
        );

        mockMvc.perform(post("/api/user/schedule/alice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isOk());
    }
}