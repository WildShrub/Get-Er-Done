package com.geterdone.get_er_done.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.UserGroups;
import com.geterdone.get_er_done.service.UserGroupsService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserGroupsController.class)
class UserGroupsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserGroupsService service;

    @Autowired
    private ObjectMapper objectMapper;

    /* ---------------- GET /{username} ---------------- */

    @Test
    void getUserGroups_returnsUserGroups() throws Exception {
        UserGroups groups = new UserGroups();
        groups.setUsername("alice");

        when(service.getUserGroupss("alice")).thenReturn(groups);

        mockMvc.perform(get("/api/user/groups/alice"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username").value("alice"));
    }

    /* ---------------- POST /save ---------------- */

    @Test
    void saveUserGroups_delegatesToService() throws Exception {
        UserGroups groups = new UserGroups();
        groups.setUsername("bob");

        mockMvc.perform(post("/api/user/groups/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(groups)))
               .andExpect(status().isOk());

        verify(service).saveUserGroupss(any(UserGroups.class));
    }

    /* ---------------- POST /{username}/add/{group} ---------------- */

    @Test
    void addGroup_delegatesToService() throws Exception {
        mockMvc.perform(post("/api/user/groups/alice/add/group1"))
               .andExpect(status().isOk());

        verify(service).addGroup("alice", "group1");
    }

    /* ---------------- POST /{username}/remove/{group} ---------------- */

    @Test
    void removeGroup_delegatesToService() throws Exception {
        mockMvc.perform(post("/api/user/groups/bob/remove/group2"))
               .andExpect(status().isOk());

        verify(service).removeGroup("bob", "group2");
    }
}
