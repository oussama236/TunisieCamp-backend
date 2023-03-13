package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Commandes;
import com.gladiators.pi_spring.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;


@RestController
    @RequestMapping("/Commandes")

    public class CommandeController {
    @Autowired
    private CommandeService commandeService;

    @GetMapping("/getall")
    public List<Commandes> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @PostMapping("/pot")
    public Commandes createCommande(@RequestBody Commandes commandes) {
        return commandeService.createCommande(commandes);
    }
    @DeleteMapping("/{IdCommandes}")
    public ResponseEntity<Commandes> deleteCommande(@PathVariable long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.ok().build();
    }
       /* @PostMapping("/sendEmail")
        public void sendEmail(@RequestBody Commandes commandes) throws MessagingException {
            commandeService.sendConfirmationEmail(commandes);
        }*/


    }



