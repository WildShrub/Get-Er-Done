package com.geterdone.get_er_done.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geterdone.get_er_done.model.UserSchedule;
import com.geterdone.get_er_done.repository.UserScheduleRepository;

@Service
public class UserScheduleService {

    @Autowired
    private UserScheduleRepository repository;

    public UserSchedule getSchedule(String username) {
        return repository.get(username);
    }

    public void updateSchedule(String username, String json) {
        repository.save(new UserSchedule(username, json));
    }
}