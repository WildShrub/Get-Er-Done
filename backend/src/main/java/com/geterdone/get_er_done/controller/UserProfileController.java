package com.geterdone.get_er_done.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.model.UserProfile;
import com.geterdone.get_er_done.service.UserProfileService;

@RestController
@RequestMapping("/api/user/profile")
public class UserProfileController {

    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    // Fetch user profile
    @GetMapping("/{username}")
    public UserProfile getProfile(@PathVariable String username) {
        return service.getUserProfiles(username);
    }

    // Create
    @PostMapping("/save")
    public void saveProfile(@RequestBody UserProfile profile) {
        service.saveUserProfiles(profile);
    }

    // Update user profile
    @PostMapping("/{username}/update")
    public void updateProfile(@PathVariable String username, @RequestBody UserProfile updatedProfile) {
        service.updateUserProfiles(username, updatedProfile);
    }
}