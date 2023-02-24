package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Livraison;
import com.gladiators.pi_spring.Services.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @GetMapping("/{idCommande}")
    public Livraison getLivraisonByCommandeId(@PathVariable Long idCommande) {
        return livraisonService.getLivraisonByCommandeId(idCommande);
    }
}

