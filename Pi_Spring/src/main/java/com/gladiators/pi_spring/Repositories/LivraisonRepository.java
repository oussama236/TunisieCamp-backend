package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivraisonRepository extends JpaRepository<Livraison, Long> {

    Livraison findByCommandeIdCommande(Long idCommande);

}
