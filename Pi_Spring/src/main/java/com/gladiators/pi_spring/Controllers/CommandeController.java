package com.gladiators.pi_spring.Controllers;

import com.gladiators.pi_spring.Entities.Commandes;
import com.gladiators.pi_spring.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
    @RequestMapping("/commandes")

    public class CommandeController {
        @Autowired
        private CommandeService commandeService;

        @GetMapping("/{IdCommande}")
        public Commandes getCommandeById(@PathVariable Long IdCommande) {
            return commandeService.getCommandeById(IdCommande);
        }

        @PostMapping("/Post")
        public Commandes createCommande(@RequestBody Commandes commandes) {
            return commandeService.saveCommande(commandes);
        }

        @PutMapping("/Update/{id}")
        public Commandes updateCommande(@PathVariable Long IdCommande, @RequestBody Commandes commandes) {
            commandes.setIdCommandes(IdCommande);
            return commandeService.updateCommande(commandes);
        }

        @DeleteMapping("/Delete/{IdCommande}")
        public void deleteCommande(@PathVariable Long IdCommande) {
            commandeService.deleteCommandeById(IdCommande);
        }
       /* @PostMapping("/sendEmail")
        public void sendEmail(@RequestBody Commandes commandes) throws MessagingException {
            commandeService.sendConfirmationEmail(commandes);
        }*/


    }



