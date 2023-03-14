package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Commentaire;
import com.gladiators.pi_spring.Entities.Likes;
import com.gladiators.pi_spring.Repositories.CommentaireRepository;
import com.gladiators.pi_spring.Services.Interfaces.IServiceCampingCentre;
import com.gladiators.pi_spring.Services.Interfaces.IServiceCommentaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/Commentaire")

public class CommentaireController {
    @Autowired
    IServiceCommentaire iServiceCommentaire;
    @Autowired
    IServiceCampingCentre iServiceCampingCentre;
    @Autowired
    CommentaireRepository commentaireRepository;

    @PostMapping("/ajouterCommentaire/{idCentre}")
    @ResponseBody
    public ResponseEntity<Commentaire> ajouterCommentaire(@PathVariable Integer idCentre, @RequestBody Commentaire commentaire) {
        Commentaire nouveauCommentaire = iServiceCommentaire.ajouterCommentaire(commentaire, idCentre);
        return ResponseEntity.ok(nouveauCommentaire);
    }

    @DeleteMapping("/{idCommentaire}")
    public ResponseEntity<String> supprimerCommentaire(@PathVariable("idCommentaire") Integer idCommentaire) {
        try {
            iServiceCommentaire.supprimerCommentaireParId(idCommentaire);
            return new ResponseEntity<>("Le commentaire avec l'ID " + idCommentaire + " a été supprimé avec succès", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/nb-commentaire-par-centre/{id_centre}")
    public Integer nbCommentParCentre(@PathVariable("id_centre") Integer id_centre) {
        return iServiceCommentaire.nbCommentParCenter(id_centre);
    }

    @PutMapping("/modifierCommentaire/{idCommentaire}")
    @ResponseBody
    public Commentaire modifierCommentaire(@PathVariable("idCommentaire") Integer idCommentaire, @RequestBody Commentaire commentaire) {
        return iServiceCommentaire.updateCommentaire(idCommentaire, commentaire);
    }

    @GetMapping(value = "/list/{id_centre}")
    @ResponseBody
    public List<Commentaire> getAllCommentaires(@PathVariable("id_centre") Integer id_centre) {
        return iServiceCommentaire.afficheComment(id_centre) ;
    }
    @PostMapping("/add-Like-comment/{idComment}/{userId}")
    @ResponseBody
    public Likes addLike_to_comment(@RequestBody Likes commentLiked, @PathVariable("idComment") Integer idComment, @PathVariable("userId") Long userId) {
        Likes activityLiked1 = new Likes ();
        activityLiked1.setIsLiked (true);

        return iServiceCommentaire.addLikeToComment (commentLiked, idComment, userId);
    }

    @DeleteMapping("/Delete-Like/{IdLike}/{idUser}")
    public ResponseEntity<?> Delete_Like(@PathVariable("IdLike") Long IdLike, @PathVariable("idUser") Long idUser) {
        return iServiceCommentaire.Delete_Like (IdLike, idUser);
    }
}


