package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubInterface {
    public Publication AddPublication(Publication publication);
    void deletePublication(Long id_pb);

    public Publication updatePublication(Publication publication);

    List<Publication> retrieveAllPubs();
    void nbrLikesPosts();

}
