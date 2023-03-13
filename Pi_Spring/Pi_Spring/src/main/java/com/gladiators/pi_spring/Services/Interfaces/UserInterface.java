package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.User;

import java.util.List;

public interface UserInterface {


   public User AddUserAndAffectRole(User user, Long roleId);
   public String deleteUserById(long id);

    public User updateUser(User user);

    List<User> retrieveAllUser();

}
