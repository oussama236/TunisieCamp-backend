package com.gladiators.pi_spring.Services.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gladiators.pi_spring.Entities.Groupez;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.GroupRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.GroupInterface;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupImp implements GroupInterface {
    @Autowired
    GroupRepository groupRepository;
    UserRepository userRepository;
    @Override
    public Groupez AddGroupAndMembers(Groupez group){
        groupRepository.save(group);
        return group;

    }
    @Override
    public Groupez updateGroup(Groupez group){
        groupRepository.save(group);
        return group;
    }
    @Override
    public void deleteGroup(Long id){
        groupRepository.deleteById(id);
    }
    public void affectMembersToGroup(String firstName,String lastName ,Groupez group){
        User u =userRepository.findByFirstNameAndLastName(firstName, lastName);
        group.getMembers().add(u);
        groupRepository.save(group);
    }

}
