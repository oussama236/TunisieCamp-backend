package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {

    Livraison findByIdCommande(Long idCommande);

    Livraison findByEmplacement(String emplacement);

    // Ajouter une méthode pour mettre à jour l'emplacement d'une Livraison
    @Modifying
    @Query("update Livraison set emplacement = :emplacement where IdLivraison = :IdLivraison")
    void updateEmplacement(@Param("livraisonId") Long livraisonId, @Param("emplacement") String emplacement);
}

