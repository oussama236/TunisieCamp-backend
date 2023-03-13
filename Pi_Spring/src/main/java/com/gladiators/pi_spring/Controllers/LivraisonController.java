package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Livraison;
import com.gladiators.pi_spring.Services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @GetMapping("/{idCommande}")
    public Livraison getLivraisonByCommandeId(@PathVariable Long idCommande) {
        return livraisonService.getLivraisonByCommandeId(idCommande);
    }

    @PostMapping("/{IdCommandes}/emplacement/{emplacement}")
    public void updateEmplacementCommande(@PathVariable Long IdCommandes, @PathVariable String emplacement) {
        livraisonService.updateEmplacementCommandes(IdCommandes, emplacement);
    }

    @GetMapping("/{IdCommandes}")
    public String getEmplacementCommande(@PathVariable Long IdCommandes) {
        String emplacement = livraisonService.getEmplacementCommande(IdCommandes);

        if (emplacement != null) {
            return "L'emplacement de la commande est : " + emplacement;
        } else {
            return "Aucune livraison en cours pour la commande.";
        }
    }
}

