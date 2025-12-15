package com.geterdone.get_er_done.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.model.GroupTask;
import com.geterdone.get_er_done.service.GroupTaskService;

@RestController
@RequestMapping("/api/group/tasks")
public class GroupTaskController {
    
    private final GroupTaskService service;

    public GroupTaskController(GroupTaskService service) {
        this.service = service;
    }

    @GetMapping("/{groupID}")
    public GroupTask getTasks(@PathVariable String groupID) {
        return service.getGroupTasks(groupID);
    }

    @PostMapping("/save")
    public void saveTasks(@RequestBody GroupTask tasks) {
        service.saveGroupTasks(tasks);
    }

    @PostMapping("/{groupID}/add")
    public void addTask(@PathVariable String groupID, @RequestBody com.geterdone.get_er_done.model.GroupTaskItem task) {
        service.addTask(groupID, task);
    }

    @PatchMapping("/{groupID}/{taskId}/completion")
    public void updateCompletionStatus(
        @PathVariable String groupID,
        @PathVariable String taskId,
        @RequestBody com.geterdone.get_er_done.dto.task.CompletionRequest request) {

        service.changeCompletionStatus(groupID, taskId, request.completed());
    }

    @PostMapping("/{groupID}/sort/priority")
    public ResponseEntity<Void> sortByPriority(@PathVariable String groupID) {
        service.sortByPriority(groupID);
        return ResponseEntity.ok().build();
    }
}