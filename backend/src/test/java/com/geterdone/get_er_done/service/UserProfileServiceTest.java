package com.geterdone.get_er_done.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geterdone.get_er_done.model.UserProfile;
import com.geterdone.get_er_done.repository.UserProfileRepository;

@ExtendWith(MockitoExtension.class)
class UserProfileServiceTest {

    @Mock
    private UserProfileRepository repository;

    @InjectMocks
    private UserProfileService service;

    /* ---------------- getUserProfiles ---------------- */

    @Test
    void getUserProfiles_returnsProfileFromRepository() {
        UserProfile profile = new UserProfile();
        profile.setUsername("alice");

        when(repository.findByUsername("alice")).thenReturn(profile);

        UserProfile result = service.getUserProfiles("alice");

        assertEquals("alice", result.getUsername());
        verify(repository).findByUsername("alice");
    }

    /* ---------------- saveUserProfiles ---------------- */

    @Test
    void saveUserProfiles_delegatesToRepository() {
        UserProfile profile = new UserProfile();

        service.saveUserProfiles(profile);

        verify(repository).save(profile);
    }

    /* ---------------- updateUserProfiles ---------------- */

    @Test
    void updateUserProfiles_updatesAndSaves_whenProfileExists() {
        UserProfile existing = new UserProfile();
        existing.setUsername("alice");
        existing.setEmail("old@example.com");
        existing.setPfp("old.png");

        UserProfile updated = new UserProfile();
        updated.setUsername("alice");
        updated.setEmail("new@example.com");
        updated.setPfp("new.png");

        when(repository.findByUsername("alice")).thenReturn(existing);

        service.updateUserProfiles("alice", updated);

        assertEquals("alice", existing.getUsername());
        assertEquals("new@example.com", existing.getEmail());
        assertEquals("new.png", existing.getPfp());

        verify(repository).save(existing);
    }

    @Test
    void updateUserProfiles_doesNothing_whenProfileDoesNotExist() {
        when(repository.findByUsername("missing")).thenReturn(null);

        service.updateUserProfiles("missing", new UserProfile());

        verify(repository, never()).save(any());
    }
}
