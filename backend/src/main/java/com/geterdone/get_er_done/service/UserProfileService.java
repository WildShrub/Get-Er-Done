package com.geterdone.get_er_done.service;

import org.springframework.stereotype.Service;

import com.geterdone.get_er_done.model.UserProfile;
import com.geterdone.get_er_done.repository.UserProfileRepository;

@Service
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    public UserProfile getUserProfiles(String username) {
        return repository.findByUsername(username);
    }

    public void saveUserProfiles(UserProfile profile) {
        repository.save(profile);
    }

    public void updateUserProfiles(String username, UserProfile updatedProfile) {
        UserProfile existingProfile = repository.findByUsername(username);
        if (existingProfile != null) {
            existingProfile.setUsername(updatedProfile.getUsername());
            existingProfile.setEmail(updatedProfile.getEmail());
            existingProfile.setPfp(updatedProfile.getPfp());
            repository.save(existingProfile);
        }
    }
}