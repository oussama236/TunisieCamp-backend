package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.LikeRepository;
import com.gladiators.pi_spring.Repositories.PublicationRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.LikeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikeController {
@Autowired
    LikeInterface likeInterface;
@Autowired
    LikeRepository likeRepository;
@Autowired
    PublicationRepository publicationRepository;
@Autowired
    UserRepository userRepository;
    @PostMapping("/{id_pb}/{id}")
    @ResponseBody
    public void likePublication(@PathVariable Long id_pb,@PathVariable Long id) {
        Publication p = publicationRepository.findById(id_pb).get();
        User u = userRepository.findById(id).get();
        Long x;
        if (likeRepository.findLikesByUserAndPublication(u, p) != null) {
             x = likeRepository.findLikesByUserAndPublication(u,p).getId_like();
             likeInterface.unlikePublication(x);
        } else
            likeInterface.likePublication(id_pb, id);
    }
        @DeleteMapping("/{id_like}")
    public void unlikePublication(@PathVariable Long id_like){
       likeInterface.unlikePublication(id_like);
    }

}
