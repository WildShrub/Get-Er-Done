package com.geterdone.get_er_done.service;

import com.geterdone.get_er_done.model.GroupTask;
import com.geterdone.get_er_done.repository.GroupTaskRepository;


import org.springframework.stereotype.Service;

@Service
public class GroupTaskService {

    private final GroupTaskRepository repository;

    public GroupTaskService(GroupTaskRepository repository) {
        this.repository = repository;
    }

    public GroupTask getGroupTasks(String groupID) {
        return repository.findByGroupID(groupID);
    }

    public void saveGroupTasks(GroupTask tasks) {
        repository.save(tasks);
    }
    
}
