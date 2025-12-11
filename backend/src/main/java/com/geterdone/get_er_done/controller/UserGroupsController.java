package com.geterdone.get_er_done.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.model.UserGroups;
import com.geterdone.get_er_done.service.UserGroupsService;

@RestController
@RequestMapping("/api/user/groups")
public class UserGroupsController {

    private final UserGroupsService service;

    public UserGroupsController(UserGroupsService service) {
        this.service = service;
    }

    // Fetch user group list
    @GetMapping("/{username}")
    public UserGroups getUserGroups(@PathVariable String username) {
        return service.getUserGroupss(username);
    }

    // Create/update user group list
    @PostMapping("/save")
    public void saveUserGroups(@RequestBody UserGroups groups) {
        service.saveUserGroupss(groups);
    }
}