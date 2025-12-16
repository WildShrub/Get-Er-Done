package com.geterdone.get_er_done.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.GroupSchedule;
import com.geterdone.get_er_done.service.GroupScheduleService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GroupScheduleController.class)
class GroupScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupScheduleService service;

    @Autowired
    private ObjectMapper objectMapper;

    /* ---------------- GET /{groupID} ---------------- */

    @Test
    void getSchedule_returnsGroupSchedule() throws Exception {
        GroupSchedule schedule = new GroupSchedule();
        schedule.setGroupID("group1");

        when(service.getGroupSchedule("group1")).thenReturn(schedule);

        mockMvc.perform(get("/api/group/schedule/group1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.groupID").value("group1"));
    }

    /* ---------------- POST /save ---------------- */

    @Test
    void saveSchedule_delegatesToService() throws Exception {
        GroupSchedule schedule = new GroupSchedule();
        schedule.setGroupID("group2");

        mockMvc.perform(post("/api/group/schedule/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(schedule)))
               .andExpect(status().isOk());

        verify(service).saveGroupSchedule(any(GroupSchedule.class));
    }

    /* ---------------- POST /{groupID}/create ---------------- */

    @Test
    void createSchedule_delegatesToService() throws Exception {
        mockMvc.perform(post("/api/group/schedule/group3/create"))
               .andExpect(status().isOk());

        verify(service).createGroupSchedule("group3");
    }
}
