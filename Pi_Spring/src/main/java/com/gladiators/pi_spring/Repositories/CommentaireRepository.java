package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

@EnableJpaRepositories

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

    @Query("SELECT COUNT(c) FROM Commentaire c WHERE c.camping_centre.idCentre = :idCentre")
    Integer nbCommentParCenter(@Param("idCentre") Integer id_centre);

}
