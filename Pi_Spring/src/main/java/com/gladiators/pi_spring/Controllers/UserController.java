package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Services.Implementations.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/userbyid/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        User user = userImp.findUserById(id);
        if (user == null){
            return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }
}
