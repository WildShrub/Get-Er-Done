package com.geterdone.get_er_done.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.geterdone.get_er_done.model.UserGroups;
import com.geterdone.get_er_done.repository.UserGroupsRepository;

@ExtendWith(MockitoExtension.class)
class UserGroupsServiceTest {

    @Mock
    private UserGroupsRepository repository;

    @InjectMocks
    private UserGroupsService service;

    /* ---------------- getUserGroupss ---------------- */

    @Test
    void getUserGroupss_returnsUserGroups() {
        UserGroups groups = new UserGroups();
        groups.setUsername("alice");

        when(repository.findByUsername("alice")).thenReturn(groups);

        UserGroups result = service.getUserGroupss("alice");

        assertEquals("alice", result.getUsername());
        verify(repository).findByUsername("alice");
    }

    /* ---------------- saveUserGroupss ---------------- */

    @Test
    void saveUserGroupss_savesToRepository() {
        UserGroups groups = new UserGroups();

        service.saveUserGroupss(groups);

        verify(repository).save(groups);
    }

    /* ---------------- addGroup ---------------- */

    @Test
    void addGroup_addsGroupAndSaves_whenUserExists() {
        List<String> groupIds = new ArrayList<>();
        groupIds.add("group1");

        UserGroups groups = new UserGroups();
        groups.setUsername("alice");
        groups.setGroupsID(groupIds);

        when(repository.findByUsername("alice")).thenReturn(groups);

        service.addGroup("alice", "group2");

        assertTrue(groups.getGroupsID().contains("group2"));
        verify(repository).save(groups);
    }

    @Test
    void addGroup_doesNothing_whenUserDoesNotExist() {
        when(repository.findByUsername("missing")).thenReturn(null);

        service.addGroup("missing", "group1");

        verify(repository, never()).save(any());
    }

    /* ---------------- removeGroup ---------------- */

    @Test
    void removeGroup_removesGroupAndSaves_whenUserExists() {
        List<String> groupIds = new ArrayList<>();
        groupIds.add("group1");
        groupIds.add("group2");

        UserGroups groups = new UserGroups();
        groups.setUsername("alice");
        groups.setGroupsID(groupIds);

        when(repository.findByUsername("alice")).thenReturn(groups);

        service.removeGroup("alice", "group1");

        assertFalse(groups.getGroupsID().contains("group1"));
        verify(repository).save(groups);
    }

    @Test
    void removeGroup_doesNothing_whenUserDoesNotExist() {
        when(repository.findByUsername("missing")).thenReturn(null);

        service.removeGroup("missing", "group1");

        verify(repository, never()).save(any());
    }
}
