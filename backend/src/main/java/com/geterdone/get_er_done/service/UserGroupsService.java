package com.geterdone.get_er_done.service;

import org.springframework.stereotype.Service;

import com.geterdone.get_er_done.model.UserGroups;
import com.geterdone.get_er_done.repository.UserGroupsRepository;

@Service
public class UserGroupsService {

    private final UserGroupsRepository repository;

    public UserGroupsService(UserGroupsRepository repository) {
        this.repository = repository;
    }

    public UserGroups getUserGroupss(String username) {
        return repository.findByUsername(username);
    }

    public void saveUserGroupss(UserGroups userGroups) {
        repository.save(userGroups);
    }
}