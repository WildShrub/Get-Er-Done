package com.geterdone.get_er_done.service;

import org.springframework.stereotype.Service;

import com.geterdone.get_er_done.model.GroupSchedule;
import com.geterdone.get_er_done.repository.GroupScheduleRepository;

@Service
public class GroupScheduleService {
    
    private final GroupScheduleRepository repository;
    //create the service and connect it to a repository
    public GroupScheduleService(GroupScheduleRepository repository) {
        this.repository = repository;
    }
    //getters and setters
    public GroupSchedule getGroupSchedule(String groupID) {
        return repository.findByGroupID(groupID);
    }

    public void saveGroupSchedule(GroupSchedule schedule) {
        repository.save(schedule);
    }

    public void createGroupSchedule(String groupID) {
        GroupSchedule schedule = new GroupSchedule();
        schedule.setGroupID(groupID);
        repository.save(schedule);
    }
}
