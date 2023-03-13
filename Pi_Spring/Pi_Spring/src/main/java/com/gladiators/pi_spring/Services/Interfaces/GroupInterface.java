package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.Groupez;
import com.gladiators.pi_spring.Entities.Publication;

import java.util.List;

public interface GroupInterface {
    public Groupez AddGroupAndMembers(Groupez group);
    void deleteGroup(Long id);

    public Groupez updateGroup(Groupez group);
    public void affectMembersToGroup(String firstName,String lastName ,Groupez group);

}
