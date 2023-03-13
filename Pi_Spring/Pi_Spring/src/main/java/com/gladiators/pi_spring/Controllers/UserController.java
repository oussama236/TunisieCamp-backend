package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Services.Implementations.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {

    @Autowired
    UserImp userImp;

    @PostMapping("/signup/{idRole}")
    @ResponseBody
    public User signup(@RequestBody User user , @PathVariable("idRole") Long idRole ) {
        return  userImp.AddUserAndAffectRole(user,idRole);
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        return userImp.deleteUserById(id);

    }

    @PostMapping("/update")
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        userImp.updateUser(user);
        return user;
    }

    @GetMapping("/list")
    public List<User> showUsers() {
        return userImp.retrieveAllUser();

    }
}
