package com.geterdone.get_er_done.controller;

import org.springframework.web.bind.annotation.*;

import com.geterdone.get_er_done.model.GroupProfile;
import com.geterdone.get_er_done.service.GroupProfileService;
import com.geterdone.get_er_done.dto.task.GroupNameRequest;

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
    public void createGroupProfile(@RequestBody GroupNameRequest request) {
        service.createGroupProfile(request.getGroupName());
    }

    @PostMapping("/rename")
    public void renameGroupProfile(@RequestBody GroupProfile groupProfile) {
        service.saveGroupProfile(groupProfile);
    }
}
