package com.geterdone.get_er_done.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geterdone.get_er_done.model.UserLogin;
import com.geterdone.get_er_done.service.UserLoginService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserLoginController.class)
class UserLoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserLoginService service;

    @Autowired
    private ObjectMapper objectMapper;

    /* ---------------- POST /register ---------------- */

    @Test
    void registerUser_delegatesToService() throws Exception {
        UserLogin login = new UserLogin();
        login.setUsername("alice");
        login.setPasswordHash("password123");

        mockMvc.perform(post("/api/login/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(login)))
               .andExpect(status().isOk());

        verify(service).registerUser(any(UserLogin.class));
    }

    /* ---------------- POST /authenticate ---------------- */

    @Test
    void authenticate_returnsTrueWhenValid() throws Exception {
        when(service.validateLogin("alice", "password123")).thenReturn(true);

        mockMvc.perform(post("/api/login/authenticate")
                .param("username", "alice")
                .param("password", "password123"))
               .andExpect(status().isOk())
               .andExpect(content().string("true"));
    }

    @Test
    void authenticate_returnsFalseWhenInvalid() throws Exception {
        when(service.validateLogin("alice", "wrongpass")).thenReturn(false);

        mockMvc.perform(post("/api/login/authenticate")
                .param("username", "alice")
                .param("password", "wrongpass"))
               .andExpect(status().isOk())
               .andExpect(content().string("false"));
    }

    /* ---------------- GET /{username} ---------------- */

    @Test
    void getUser_returnsUserLogin() throws Exception {
        UserLogin login = new UserLogin();
        login.setUsername("bob");
        login.setPasswordHash("hashed");

        when(service.getUserLogin("bob")).thenReturn(login);

        mockMvc.perform(get("/api/login/bob"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username").value("bob"))
               .andExpect(jsonPath("$.passwordHash").value("hashed"));
    }
}