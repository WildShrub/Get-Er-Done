package com.geterdone.get_er_done.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geterdone.get_er_done.model.GroupProfile;
import com.geterdone.get_er_done.repository.GroupProfileRepository;

@ExtendWith(MockitoExtension.class)
class GroupProfileServiceTest {

    @Mock
    private GroupProfileRepository repository;

    @InjectMocks
    private GroupProfileService service;

    /* ---------------- createGroupProfile ---------------- */

    @Test
    void createGroupProfile_createsAndSavesGroupProfile() {
        service.createGroupProfile("My Group");

        verify(repository).save(argThat(profile ->
            "My Group".equals(profile.getGroupName())
        ));
    }

    /* ---------------- getGroupProfile ---------------- */

    @Test
    void getGroupProfile_returnsProfileFromRepository() {
        GroupProfile profile = new GroupProfile("group1", "Test Group");

        when(repository.findByGroupID("group1")).thenReturn(profile);

        GroupProfile result = service.getGroupProfile("group1");

        assertEquals("group1", result.getGroupID());
        verify(repository).findByGroupID("group1");
    }

    /* ---------------- saveGroupProfile ---------------- */

    @Test
    void saveGroupProfile_delegatesToRepository() {
        GroupProfile profile = new GroupProfile("group1", "Test Group");

        service.saveGroupProfile(profile);

        verify(repository).save(profile);
    }
}
