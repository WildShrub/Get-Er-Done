package com.geterdone.get_er_done.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.model.GroupSchedule;
import com.geterdone.get_er_done.service.GroupScheduleService;

@RestController
@RequestMapping("/api/group/schedule")
public class GroupScheduleController {
    
    private final GroupScheduleService service;

    public GroupScheduleController(GroupScheduleService service) {
        this.service = service;
    }

    // Fetch group schedule
    @GetMapping("/{groupID}")
    public GroupSchedule getSchedule(@PathVariable String groupID) {
        return service.getGroupSchedule(groupID);
    }

    // Create/update schedule
    @PostMapping("/save")
    public void saveSchedule(@RequestBody GroupSchedule schedule) {
        service.saveGroupSchedule(schedule);
    }

    // Create a new schedule for a group
    @PostMapping("/{groupID}/create")
    public void createSchedule(@PathVariable String groupID) {
        service.createGroupSchedule(groupID);
    }
}
