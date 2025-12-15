package com.geterdone.get_er_done.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.geterdone.get_er_done.model.GroupTask;
import com.geterdone.get_er_done.model.GroupTaskItem;
import com.geterdone.get_er_done.model.UserTask;
import com.geterdone.get_er_done.model.UserTaskItem;
import com.geterdone.get_er_done.repository.GroupTaskRepository;

@Service
public class GroupTaskService {

    private final GroupTaskRepository repository;

    private final ObjectMapper mapper = new ObjectMapper();

    public GroupTaskService(GroupTaskRepository repository) {
        this.repository = repository;
    }

    public GroupTask getGroupTasks(String groupID) {
        return repository.findByGroupID(groupID);
    }

    public void saveGroupTasks(GroupTask tasks) {
        repository.save(tasks);
    }

    public void addTask(String groupID, GroupTaskItem task) {
        try {
            GroupTask groupTasks = repository.findByGroupID(groupID);

            List<GroupTaskItem> taskList;
            if (groupTasks == null || groupTasks.getTasksJson() == null || groupTasks.getTasksJson().isEmpty()) {
                taskList = new ArrayList<>();
                groupTasks = new GroupTask();
                groupTasks.setGroupID(groupID);
            } else {
                taskList = mapper.readValue(
                    groupTasks.getTasksJson(),
                    new TypeReference<List<GroupTaskItem>>() {}
                );
            }

            task.setTaskID(UUID.randomUUID().toString());

            taskList.add(task);

            String jsonOut = mapper.writeValueAsString(taskList);

            groupTasks.setTasksJson(jsonOut);
            repository.save(groupTasks);

        } catch (Exception e) {
            throw new RuntimeException("Failed to add task", e);
        }
    }

    public void changeCompletionStatus(String groupID, String taskID, boolean completed) {
        try {
            GroupTask groupTasks = repository.findByGroupID(groupID);
            if (groupTasks == null || groupTasks.getTasksJson() == null || groupTasks.getTasksJson().isEmpty()) {
                throw new RuntimeException("No tasks found for group: " + groupID);
            }

            List<GroupTaskItem> taskList = mapper.readValue(
                groupTasks.getTasksJson(),
                new TypeReference<List<GroupTaskItem>>() {}
            );

            for (GroupTaskItem task : taskList) {
                if (task.getTaskID().equals(taskID)) {
                    task.setCompleted(completed);
                    break;
                }
            }

            String jsonOut = mapper.writeValueAsString(taskList);
            groupTasks.setTasksJson(jsonOut);
            repository.save(groupTasks);

        } catch (Exception e) {
            throw new RuntimeException("Failed to change completion status", e);
        }
    }

    public void sortByPriority(String groupID) {
        try {
            GroupTask groupTasks = repository.findByGroupID(groupID);
            if (groupTasks == null || groupTasks.getTasksJson() == null || groupTasks.getTasksJson().isEmpty()) {
                throw new RuntimeException("No tasks found for group: " + groupID);
            }

            List<GroupTaskItem> taskList = mapper.readValue(
                groupTasks.getTasksJson(),
                new TypeReference<List<GroupTaskItem>>() {}
            );

            taskList.sort((t1, t2) -> {
                int p1 = Integer.parseInt(t1.getPriority());
                int p2 = Integer.parseInt(t2.getPriority());
                return Integer.compare(p1, p2);
            });

            String jsonOut = mapper.writeValueAsString(taskList);
            groupTasks.setTasksJson(jsonOut);
            repository.save(groupTasks);

        } catch (Exception e) {
            throw new RuntimeException("Failed to sort tasks by priority", e);
        }
    }
    
}
