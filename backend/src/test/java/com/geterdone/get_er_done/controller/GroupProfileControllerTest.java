package com.geterdone.get_er_done.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.GroupProfile;
import com.geterdone.get_er_done.service.GroupProfileService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(GroupProfileController.class)
class GroupProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupProfileService service;

    @Autowired
    private ObjectMapper objectMapper;

    /* ---------------- GET /{groupID} ---------------- */

    @Test
    void getGroupProfile_returnsGroupProfile() throws Exception {
        GroupProfile profile = new GroupProfile("grp1", "My Group");

        when(service.getGroupProfile("grp1")).thenReturn(profile);

        mockMvc.perform(get("/api/group/profile/grp1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.groupID").value("grp1"))
               .andExpect(jsonPath("$.groupName").value("My Group"));
    }

    /* ---------------- POST /create ---------------- */

    @Test
    void createGroupProfile_delegatesToService() throws Exception {
        String groupName = "New Group";

        mockMvc.perform(post("/api/group/profile/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groupName)))
               .andExpect(status().isOk());

        verify(service).createGroupProfile(groupName);
    }

    /* ---------------- POST /rename ---------------- */

    @Test
    void renameGroupProfile_delegatesToService() throws Exception {
        GroupProfile profile = new GroupProfile("grp2", "Renamed Group");

        mockMvc.perform(post("/api/group/profile/rename")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(profile)))
               .andExpect(status().isOk());

        verify(service).saveGroupProfile(any(GroupProfile.class));
    }
}
