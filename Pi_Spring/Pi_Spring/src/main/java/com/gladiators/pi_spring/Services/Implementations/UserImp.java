package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.Role;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.RoleRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.metrics.jfr.FlightRecorderApplicationStartup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImp implements UserInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired

    RoleRepository roleRepository;
    @Override
    public User AddUserAndAffectRole(User user, Long roleId) {
        Role role= roleRepository.findById(roleId).get();
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

}
