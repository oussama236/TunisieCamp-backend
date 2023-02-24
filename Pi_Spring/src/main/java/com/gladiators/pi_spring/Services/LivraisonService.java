package com.gladiators.pi_spring.Services;

import com.gladiators.pi_spring.Entities.Livraison;
import com.gladiators.pi_spring.Repositories.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    public Livraison getLivraisonByCommandeId(Long idCommande) {
        return livraisonRepository.findByCommandeIdCommande(idCommande);
    }
}
