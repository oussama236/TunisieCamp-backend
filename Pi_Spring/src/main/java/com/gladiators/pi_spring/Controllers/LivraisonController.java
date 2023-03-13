package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Livraison;
import com.gladiators.pi_spring.Services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @GetMapping("/findbyid/{id}")
    public Optional <Livraison> getLivraisonByCommandeId(@PathVariable long id) {
        return livraisonService.getLivraisonByCommandeId(id);
    }

    @PostMapping("/{IdCommande}/emplacement/{emplacement}")
    public void updateEmplacementCommande(@PathVariable long id, @PathVariable String emplacement) {
        livraisonService.updateEmplacementCommandes(id, emplacement);
    }

    @GetMapping("/{id}")
    public String getEmplacementCommande(@PathVariable long id) {
        String emplacement = livraisonService.getEmplacementCommande(id);

        if (emplacement != null) {
            return "L'emplacement de la commande est : " + emplacement;
        } else {
            return "Aucune livraison en cours pour la commande.";
        }
    }
}

