package com.gladiators.pi_spring.Services;

import com.gladiators.pi_spring.Entities.Livraison;
import com.gladiators.pi_spring.Repositories.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    public Livraison getLivraisonByCommandeId(Long idCommande) {
        return livraisonRepository.findByIdCommande(idCommande);
    }

    private Map<Long, String> emplacements = new HashMap<>();

    public void updateEmplacementCommandes(Long IdCommandes, String emplacement) {
        emplacements.put(IdCommandes, emplacement);
    }

    public String getEmplacementCommande(Long IdCommandes) {
        return emplacements.get(IdCommandes);
    }

    public void updateEmplacementCommande(Long IdCommandes, String emplacement) {
        Livraison livraison = livraisonRepository.findByIdCommande(IdCommandes);

        if (livraison != null) {
            livraisonRepository.updateEmplacement(livraison.getCommandes().getIdCommandes(), emplacement);
        } else {
            System.out.println("la commande esxiste plus");
        }
    }
}
