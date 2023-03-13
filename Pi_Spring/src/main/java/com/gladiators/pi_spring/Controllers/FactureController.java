package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Facture;
import com.gladiators.pi_spring.Services.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @GetMapping("/{idFacture}")
    public Facture getFactureById(@PathVariable long id) {
        return factureService.getFactureById(id);
    }
}

