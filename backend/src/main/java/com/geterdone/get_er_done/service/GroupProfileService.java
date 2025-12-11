package com.geterdone.get_er_done.service;



import org.springframework.stereotype.Service;

import com.geterdone.get_er_done.model.GroupProfile;
import com.geterdone.get_er_done.repository.GroupProfileRepository;

@Service
public class GroupProfileService {

    private final GroupProfileRepository repository;

    public GroupProfileService(GroupProfileRepository repository) {
        this.repository = repository;
    }

    // create the group
    public void createGroupProfile(String groupName) {
        //TODO: find a way to figure out what the groupID would be 
        String newID = "";
        GroupProfile groupProfile = new GroupProfile(newID,groupName);
        repository.save(groupProfile);
    }

     public GroupProfile getGroupProfile(String groupID) {
        return repository.findByGroupID(groupID);
    }

    public void saveGroupProfile(GroupProfile groupProfile) {
        repository.save(groupProfile);
    }

}
