package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.DislikeRepository;
import com.gladiators.pi_spring.Repositories.LikeRepository;
import com.gladiators.pi_spring.Repositories.PublicationRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.DislikeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dislike")
public class DislikeController {
    @Autowired
    DislikeInterface dislikeInterface;
    @Autowired
    DislikeRepository dislikeRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserRepository userRepository;
    @PostMapping("/{id_pb}/{id}")
    @ResponseBody
    public void dislikePublication(@PathVariable Long id_pb, @PathVariable Long id) {
        Publication p = publicationRepository.findById(id_pb).get();
        User u = userRepository.findById(id).get();
        Long x;
        if (dislikeRepository.findDislikesByUserAndPublication(u,p) != null) {
            x = dislikeRepository.findDislikesByUserAndPublication(u,p).getId_dis();
            dislikeInterface.undislikePublication(x);
        } else
            dislikeInterface.dislikePublication(id_pb, id);
    }
    @DeleteMapping("/{id_dis}")
    public void undislikePublication(@PathVariable Long id_dis){
        dislikeInterface.undislikePublication(id_dis);
    }

}
