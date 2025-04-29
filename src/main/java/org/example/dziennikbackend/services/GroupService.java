package org.example.dziennikbackend.services;

import org.example.dziennikbackend.repositories.GroupRepository;
import org.example.dziennikbackend.repositories.StudentInGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private StudentInGroupRepository studentInGroupRepository;
    public GroupService(GroupRepository groupRepository, StudentInGroupRepository studentInGroupRepository) {
        this.groupRepository = groupRepository;
        this.studentInGroupRepository = studentInGroupRepository;
    }



}
