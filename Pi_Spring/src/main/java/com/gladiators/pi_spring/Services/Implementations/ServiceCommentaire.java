package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.CampingCenter;
import com.gladiators.pi_spring.Entities.Commentaire;
import com.gladiators.pi_spring.Entities.Likes;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.CampingCentreRepository;
import com.gladiators.pi_spring.Repositories.CommentaireRepository;
import com.gladiators.pi_spring.Repositories.LikesRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.IServiceCommentaire;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Slf4j
@Service
public class ServiceCommentaire implements IServiceCommentaire {

    @Autowired
    CommentaireRepository commentaireRepository ;
    @Autowired
    LikesRepository likesRepository ;
    @Autowired
    CampingCentreRepository campingCentreRepository ;

    @Autowired
    UserRepository userRepository ;
    @Override
    public Commentaire ajouterCommentaire(Commentaire commentaire, Integer idCentre) {
        // Récupération du camping center correspondant à l'id fourni
        CampingCenter centre = campingCentreRepository.findById(idCentre)
                .orElseThrow(() -> new NoSuchElementException("Le camping center n'existe pas"));
        // Association du commentaire avec le camping center
        commentaire.setCamping_centre(centre);
        // Enregistrement du commentaire en base de données
        commentaire = commentaireRepository.save(commentaire);

        return commentaire;
    }
    @Override
    public Integer nbCommentParCenter(Integer id_centre)
    {
        return commentaireRepository.nbCommentParCenter(id_centre);
    }

    @Override
    public void supprimerCommentaireParId(Integer idCommentaire) {
        Optional<Commentaire> optionalCommentaire = commentaireRepository.findById(idCommentaire);
        if (optionalCommentaire.isPresent()) {
            commentaireRepository.delete(optionalCommentaire.get());
        } else {
            throw new RuntimeException("Le commentaire avec l'ID " + idCommentaire + " n'existe pas");
        }
    }

    public Commentaire updateCommentaire(Integer idCommentaire, Commentaire commentaire) {
        Optional<Commentaire> optionalCommentaire = commentaireRepository.findById(idCommentaire);
        if (optionalCommentaire.isPresent()) {
            Commentaire updatedCommentaire = optionalCommentaire.get();
            updatedCommentaire.setDateCommentaire(commentaire.getDateCommentaire());
            updatedCommentaire.setSujet(commentaire.getSujet());
            return commentaireRepository.save(updatedCommentaire);
        }
        return null;
    }
    @Override
    public List<Commentaire> afficheComment(Integer id_centre) {
        return commentaireRepository.findAll();
    }

    @Override
    public Likes addLikeToComment(Likes activityLiked, Integer idActivity, Long userId) {
        int x = 0;
        boolean y = false;
        System.out.println("start methode");
        Commentaire activity = commentaireRepository.findById(idActivity).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        for (Likes activityLiked1 : likesRepository.findAll()){
            System.out.println("start for");
            if (activityLiked1.getCommentaire().getIdCommentaire() == idActivity && activityLiked1.getUser().getId () == userId) {
                System.out.println("start if1");
                x = 1;
                y = activityLiked1.getIsLiked();
                likesRepository.delete(activityLiked1);
            }
        }
        if (x == 0 || (x == 1 && y != activityLiked.getIsLiked())) {
            activityLiked.setUser(user);
            activityLiked.setCommentaire(activity);
            likesRepository.save(activityLiked);
        }
        return activityLiked;
    }

    @Override
    public ResponseEntity<?> Delete_Like(Long idLike, Long idUser) {
        if (likesRepository.existsById(idLike)) {
            Likes postLike = likesRepository.findById(idLike)
                    .orElseThrow(() -> new EntityNotFoundException("Like Not Found"));
            User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
            if (postLike.getUser().equals(user)) {
                likesRepository.delete(postLike);
                return ResponseEntity.ok().body("Delete success");
            } else {
                return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("No permission to delete this post");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like Not Found");
        }
    }
}
