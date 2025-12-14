package com.geterdone.get_er_done.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.model.GroupProfile;
import com.geterdone.get_er_done.service.GroupProfileService;


@RestController
@RequestMapping("/api/group/profile")
public class GroupProfileController {
    
    private final GroupProfileService service;

    public GroupProfileController(GroupProfileService service) {
        this.service = service;
    }

    @GetMapping("/{groupID}")
    public GroupProfile getGroupProfile(@PathVariable String groupID) {
        return service.getGroupProfile(groupID);
    }

    @PostMapping("/create")
    public void createGroupProfile(@RequestBody String groupName) {
        service.createGroupProfile(groupName);
    }

    @PostMapping("/rename")
    public void renameGroupProfile(@RequestBody GroupProfile groupProfile) {
        service.saveGroupProfile(groupProfile);
    }
}
