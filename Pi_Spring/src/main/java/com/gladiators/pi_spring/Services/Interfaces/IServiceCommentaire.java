package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.Commentaire;
import com.gladiators.pi_spring.Entities.Likes;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface IServiceCommentaire {

    public Integer nbCommentParCenter(Integer id_centre);

    Commentaire ajouterCommentaire(Commentaire commentaire, Integer idCentre);
    public void supprimerCommentaireParId(Integer idCommentaire);
    public Commentaire updateCommentaire(Integer idCommentaire, Commentaire commentaire) ;

    public List<Commentaire> afficheComment(Integer id_centre);

    public Likes addLikeToComment(Likes activityLiked, Integer idActivity, Long userId);

    public ResponseEntity<?> Delete_Like(Long idLike , Long idUser);

}
