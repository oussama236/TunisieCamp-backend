package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.Likes;
import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.LikeRepository;
import com.gladiators.pi_spring.Repositories.PublicationRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.LikeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LikeImp implements LikeInterface {
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public void likePublication(Long id_pb,Long id){
        Publication p =publicationRepository.findById(id_pb).get();
        User u = userRepository.findById(id).get();

        Likes like = new Likes();
        like.setUser(u);
        like.setPublication(p);
        likeRepository.save(like);
    }

    @Override
    public void unlikePublication(Long id_like){
        Likes like=likeRepository.findById(id_like).get();
        likeRepository.delete(like);
    }
}
