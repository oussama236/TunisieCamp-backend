package com.gladiators.pi_spring.Services;

import com.gladiators.pi_spring.Entities.Commandes;
import com.gladiators.pi_spring.Entities.PaimentChoise;
import com.gladiators.pi_spring.Repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commandes> getAllCommandes() {
        return commandeRepository.findAll();
    }




    public Commandes createCommande(Commandes commande) {
        return commandeRepository.save(commande);
    }

    public Commandes updateCommande(Commandes commandes) {
        Commandes commandeToUpdate = commandeRepository.findById(commandes.getIdCommandes())
                .orElseThrow(() -> new EntityNotFoundException("Commande with id " + commandes.getIdCommandes() + " not found."));

        commandeToUpdate.setIdOutils(commandes.getIdOutils());
        commandeToUpdate.setPrice(commandes.getPrice());
        commandeToUpdate.setPaimentChoice(commandes.getPaimentChoice());
        commandeToUpdate.setDeliveryLocation(commandes.getDeliveryLocation());
        commandeToUpdate.setMoyenTransport(commandes.getMoyenTransport());
        commandeToUpdate.setDate(commandes.getDate());

        return commandeRepository.save(commandeToUpdate);
    }

    public void deleteCommande(Long IdCOmmandes) {
        Optional<Commandes> optionalCommande = commandeRepository.findById(IdCOmmandes);
        if (optionalCommande.isPresent()) {
            commandeRepository.deleteById(IdCOmmandes);
        } else {
            throw new EntityNotFoundException("Commande with id " + IdCOmmandes + " not found");
        }
    }

    public List<Commandes> chercherCommandesParPaimentChoice(PaimentChoise paimentChoice) {
        return commandeRepository.findByPaimentChoice(paimentChoice);
    }

    public List<Commandes> chercherCommandesParDate(Date date) {
        return commandeRepository.findByDate(date);
    }

    public List<Commandes> chercherCommandesParDeliveryLocation(String deliveryLocation) {
        return commandeRepository.findByDeliveryLocation(deliveryLocation);
    }
    }
    ////////////////////


       /* public void sendConfirmationEmail(Commandes commandes) throws MessagingException {

            JavaMailSender mailSender = null;
            MimeMessage message = mailSender .createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(commandes.getUser().getEmail());
            helper.setSubject("Confirmation de commande #" + commandes.getIdCommandes());
            helper.setText("Bonjour " + commandes.getUser().getNom() + ",\n\nNous avons bien reçu votre commande #" + commandes.getIdCommandes() + ".\n\nMerci de votre confiance !");

            mailSender.send(message);
        }*/


