package com.gladiators.pi_spring.Services;

import com.gladiators.pi_spring.Entities.Commandes;
import com.gladiators.pi_spring.Repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;



    public Commandes getCommandeById(Long IdCommande) {
        return commandeRepository.findById(IdCommande).orElse(null);
    }

    public Commandes saveCommande(Commandes commandes) {
        return commandeRepository.save(commandes);
    }

    public Commandes updateCommande(Commandes commandes) {
        return commandeRepository.save(commandes);
    }

    public void deleteCommandeById(Long IdCommande) {
        commandeRepository.deleteById(IdCommande);
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
    }

