package com.geterdone.get_er_done.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.UserProfile;
import com.geterdone.get_er_done.service.UserProfileService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserProfileController.class)
class UserProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserProfileService service;

    @Autowired
    private ObjectMapper objectMapper;

    /* ---------------- GET /{username} ---------------- */

    @Test
    void getProfile_returns200() throws Exception {
        UserProfile mockProfile = new UserProfile();
        mockProfile.setUsername("alice");
        mockProfile.setEmail("alice@example.com");

        when(service.getUserProfiles("alice")).thenReturn(mockProfile);

        mockMvc.perform(get("/api/user/profile/alice"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username").value("alice"))
               .andExpect(jsonPath("$.email").value("alice@example.com"));
    }

    /* ---------------- POST /save ---------------- */

    @Test
    void saveProfile_delegatesToService() throws Exception {
        UserProfile profile = new UserProfile();
        profile.setUsername("bob");

        mockMvc.perform(post("/api/user/profile/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(profile)))
               .andExpect(status().isOk());

        verify(service).saveUserProfiles(any(UserProfile.class));
    }

    /* ---------------- POST /{username}/update ---------------- */

    @Test
    void updateProfile_delegatesToService() throws Exception {
        UserProfile updatedProfile = new UserProfile();
        updatedProfile.setUsername("charlie");

        mockMvc.perform(post("/api/user/profile/charlie/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedProfile)))
               .andExpect(status().isOk());

        verify(service).updateUserProfiles(eq("charlie"), any(UserProfile.class));
    }
}
