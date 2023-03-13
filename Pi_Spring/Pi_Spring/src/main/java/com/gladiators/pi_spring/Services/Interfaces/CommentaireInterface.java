package com.gladiators.pi_spring.Services.Interfaces;

import java.util.List;

import com.gladiators.pi_spring.Entities.Commentaire;
import com.gladiators.pi_spring.Entities.LikeComments;

public interface CommentaireInterface {
    public Commentaire addCommentaire(Commentaire commentaire);
    public Commentaire updateCommentaire(Commentaire commentaire);
    public void deleteCommentaire(Long id_cm);
    public void affectCommentaireToPublication(Long id_cm,Long id_pb);
    public List<Commentaire> retrieveCommentairesPublicationID(Long id_pb);
    public Commentaire AddComments(Long user_id,Commentaire com,Long pub_id);
    //void sendMail(Commentaire comment, Long idUser, Long idPost);
    public void AddLikesComments(LikeComments like_com, Long user_id, Long com_id);
    public void AddloveComments(LikeComments like_com,Long user_id,Long com_id);
    public void AddHahaComments(LikeComments like_com,Long user_id,Long com_id);
    public void AddSadComments(LikeComments like_com,Long user_id,Long com_id);
    public void AddAngryComments(LikeComments like_com,Long user_id,Long com_id);
}
