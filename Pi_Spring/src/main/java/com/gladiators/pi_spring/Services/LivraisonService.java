package com.gladiators.pi_spring.Services;

import com.gladiators.pi_spring.Entities.Livraison;
import com.gladiators.pi_spring.Repositories.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LivraisonService {

    @Autowired
    private LivraisonRepository livraisonRepository;

    public Optional<Livraison>  getLivraisonByCommandeId(long id) {
        return livraisonRepository.findById(id);
    }

    private Map<Long, String> emplacements = new HashMap<>();

    public void updateEmplacementCommandes(long id, String emplacement) {
        emplacements.put(id, emplacement);
    }

    public String getEmplacementCommande(long IdCommandes) {
        return emplacements.get(IdCommandes);
    }

    public void updateEmplacementCommande(long id, String emplacement) {
       Optional <Livraison> optionalLivraison = livraisonRepository.findById(id);

        if (optionalLivraison.isPresent()) {
          Livraison livraison=  optionalLivraison.get();
          livraison.setEmplacement(emplacement);
            livraisonRepository.save(livraison);
        } else {
            System.out.println("la commande esxiste plus");
        }
    }
}
