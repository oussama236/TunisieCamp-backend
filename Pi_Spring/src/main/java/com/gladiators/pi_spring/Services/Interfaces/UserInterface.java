package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserInterface {




    User AddUserAndAffectRole(User user, Long roleId, MultipartFile file);

    public String deleteUserById(long id);

    public User updateUser(User user);

    List<User> retrieveAllUser();

    public List<User> recherche(String keyword);

}
