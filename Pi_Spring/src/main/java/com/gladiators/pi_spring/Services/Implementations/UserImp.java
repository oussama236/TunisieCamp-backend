package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.Role;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.RoleRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.FilesStorageService;
import com.gladiators.pi_spring.Services.Interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserImp implements UserInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired

    RoleRepository roleRepository;

    @Autowired
    FilesStorageService filesStorageService;
    @Override
    public User AddUserAndAffectRole(User user, Long roleId, MultipartFile file) {

        Role role= roleRepository.findById(roleId).get();
        String newName = filesStorageService.fileName(file);
        filesStorageService.save(file,newName);
        user.setPicture(newName);
        userRepository.save(user);
        role.getUsers().add(user);
        roleRepository.save(role);
        return user ;
    }

    @Override
    public String deleteUserById(long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    @Override
    public User updateUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public List<User> retrieveAllUser() {
        List<User> listUser = userRepository.findAll();
        return listUser;
    }

    @Override
    public List<User> recherche(String keyword) {
        if (keyword != null) {
            return userRepository.recherche (keyword);
        } else return userRepository.findAll ();
    }

}
