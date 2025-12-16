package com.geterdone.get_er_done.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geterdone.get_er_done.dto.task.CompletionRequest;
import com.geterdone.get_er_done.dto.task.DescriptionRequest;
import com.geterdone.get_er_done.model.UserTask;
import com.geterdone.get_er_done.model.UserTaskItem;
import com.geterdone.get_er_done.service.UserTaskService;

@RestController
@RequestMapping("/api/user/tasks")
public class UserTaskController {

    private final UserTaskService service;

    public UserTaskController(UserTaskService service) {
        this.service = service;
    }


    // Fetch user tasks
    @GetMapping("/{username}")
    public ResponseEntity<UserTask> getTasks(@PathVariable String username) {
        UserTask tasks = service.getUserTasks(username);
        return tasks != null
            ? ResponseEntity.ok(tasks)
            : ResponseEntity.notFound().build();
    }

    // Add new task
    @PostMapping("/{username}/add")
    public ResponseEntity<Void> addTask(@PathVariable String username, @RequestBody UserTaskItem task) {
        service.addTask(username, task);
        return ResponseEntity.ok().build();
    }

    // Update task completion status
    @PatchMapping("/{username}/{taskId}/completion")
    public ResponseEntity<Void> updateCompletionStatus(
        @PathVariable String username,
        @PathVariable String taskId,
        @RequestBody CompletionRequest request) {

        service.changeCompletionStatus(username, taskId, request.completed());
        return ResponseEntity.ok().build();
    }

    // Edit task description
    @PatchMapping("/{username}/{taskId}/description")
    public ResponseEntity<Void> updateDescription(
            @PathVariable String username,
            @PathVariable String taskId,
            @RequestBody DescriptionRequest request) {

        service.editDescription(username, taskId, request.description());
        return ResponseEntity.ok().build();
    }

    // Sort tasks by priority
    @PostMapping("/{username}/sort/priority")
    public ResponseEntity<Void> sortByPriority(@PathVariable String username) {
        service.sortByPriority(username);
        return ResponseEntity.ok().build();
    }



    //test api's



    //pathvariable test
    @PostMapping("/{username}/test")
    public ResponseEntity<Void> confirmPostTest(@PathVariable String username) {
        System.out.println("Test endpoint hit for user: " + username);
        return ResponseEntity.ok().build();
    }
    //simple get test
    @GetMapping("/test")
    public ResponseEntity<Void> confirmGetTest() {
        System.out.println("Test endpoint hit for get");
        return ResponseEntity.ok().build();
    }
    //post with body test
    @PostMapping("/{username}/add/test")
    public ResponseEntity<Void> addTaskTest(@PathVariable String username, @RequestBody UserTaskItem task) {
        System.out.println("request body test endpoint hit");
        service.addTask(username, task);
        return ResponseEntity.ok().build();
    }

    //sanity check
    @PostMapping("/sanity/check")
    public void test(@RequestBody Object body) {
        System.out.println(body);
    }
}
