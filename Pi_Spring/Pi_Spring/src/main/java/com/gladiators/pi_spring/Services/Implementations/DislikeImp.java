package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.Dislikes;
import com.gladiators.pi_spring.Entities.Likes;
import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.DislikeRepository;
import com.gladiators.pi_spring.Repositories.LikeRepository;
import com.gladiators.pi_spring.Repositories.PublicationRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.DislikeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DislikeImp implements DislikeInterface {
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    DislikeRepository dislikeRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void dislikePublication(Long id_pb,Long id){
        Publication p =publicationRepository.findById(id_pb).get();
        User u = userRepository.findById(id).get();
        Dislikes dislike = new Dislikes();
        dislike.setUser(u);
        dislike.setPublication(p);
        dislikeRepository.save(dislike);
    }
    @Override
         public void undislikePublication(Long id_dis){
            Dislikes dislike=dislikeRepository.findById(id_dis).get();
            dislikeRepository.delete(dislike);
        }
}
