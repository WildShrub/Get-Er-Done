package com.geterdone.get_er_done.service;

import org.springframework.stereotype.Service;

import com.geterdone.get_er_done.model.UserSchedule;
import com.geterdone.get_er_done.repository.UserScheduleRepository;

@Service
public class UserScheduleService {

    private final UserScheduleRepository repository;

    public UserScheduleService(UserScheduleRepository repository) {
        this.repository = repository;
    }

    public UserSchedule getUserSchedule(String username) {
        return repository.findByUsername(username);
    }

    public void saveUserSchedule(UserSchedule schedule) {
        repository.save(schedule);
    }

    public void createUserSchedule(String username) {
        UserSchedule schedule = new UserSchedule();
        schedule.setUsername(username);
        repository.save(schedule);
    }
}