package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.PublicationRepository;
import com.gladiators.pi_spring.Services.Interfaces.PubInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
public class PublicationImp implements PubInterface {
    @Autowired
    PublicationRepository publicationRepository;
    @Override
    public Publication AddPublication(Publication publication){
        publicationRepository.save(publication);
        return publication;
    }
    @Override
    public Publication updatePublication(Publication publication){
        publicationRepository.save(publication);
        return publication;
    }
    @Override
    public void  deletePublication(Long id_pb){
        publicationRepository.deleteById(id_pb);
    }
    @Override
    public List<Publication> retrieveAllPubs(){
        List<Publication> publicationList = publicationRepository.findAll();
        return publicationList;
    }
    @Scheduled(cron="* * */1 * * *")
    @Override
    public void nbrLikesPosts() {
        List<Publication> pubs = publicationRepository.findAll();
        pubs.stream().forEach(p->{
            log.info("l'id de la publication "+p.getId_pb()+ " a "+p.getLikes().size()+" likes et a "+p.getDislikes().size()+" dislikes");
        });
    }

}
