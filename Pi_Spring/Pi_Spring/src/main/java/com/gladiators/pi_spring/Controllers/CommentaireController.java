package com.gladiators.pi_spring.Controllers;

import java.util.List;

import com.gladiators.pi_spring.Entities.LikeComments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gladiators.pi_spring.Entities.Commentaire;
import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.CommentaireRepository;
import com.gladiators.pi_spring.Repositories.PublicationRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.CommentaireInterface;

@RestController
@RequestMapping("/Commentaire")

public class CommentaireController {
	@Autowired
	CommentaireRepository commentaireRepository;
	@Autowired
	CommentaireInterface commentaireInterface;
	@Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;
	@PostMapping("/AddComments/{userId}/{pubId}")
	public String AddComment(@RequestBody Commentaire com,@PathVariable(value = "userId") Long userId,@PathVariable(value = "pubId") Long pubId){
		List<String> dic = commentaireRepository.Dictionnaire();
		for (int i = 1; i <= dic.size(); i++) {
			if (com.getContenu_cm().contains(dic.get(i-1))) {
				break;
			}
			else{
				if (i == dic.size()) {
					User user =userRepository.findById(userId).orElse(null);
					Publication publication = publicationRepository.findById(pubId).orElse(null);
					commentaireInterface.AddComments(userId, com, pubId);
					
					
			    	String body = user.getFirstName()+" a commenté votre publication "+publication.getTitre();
					
					SimpleMailMessage mail = new SimpleMailMessage();
					mail.setTo(publication.getUser().getEmail());
					mail.setSubject("Nouveau commentaire sur votre post");
					mail.setText(body);
					javaMailSender.send(mail);
					return "comments added succesfully";
				}
			}
			
		}
		return "can not add comment which contains a forbidden word";

	}
//	 @PostMapping("/sendNotification/{idUser}/{idPost}")
//	public void sendMail(@RequestBody Commentaire comment,@PathVariable Long idUser,@PathVariable Long idPost) {
//		 comService.sendMail(comment, idUser, idPost);
//		
//	}

    @PostMapping("/AddLikesComments/{userId}/{comId}")
    public void AddLikeCom(@PathVariable("userId")Long user_id, @PathVariable("comId")Long comId, @RequestBody LikeComments like){
	commentaireInterface.AddLikesComments(like, user_id, comId);
}
	@PostMapping("/AddloveComments/{userId}/{comId}")
	public void AddloveCom(@PathVariable("userId")Long user_id,@PathVariable("comId")Long comId, @RequestBody LikeComments like){
		commentaireInterface.AddloveComments(like, user_id, comId);
	}
	@PostMapping("/AddhahaComments/{userId}/{comId}")
	public void AddHahaCom(@PathVariable("userId")Long user_id,@PathVariable("comId")Long comId, @RequestBody LikeComments like){
		commentaireInterface.AddHahaComments(like, user_id, comId);
	}
	@PostMapping("/AddsadComments/{userId}/{comId}")
	public void AddsadCom(@PathVariable("userId")Long user_id,@PathVariable("comId")Long comId, @RequestBody LikeComments like){
		commentaireInterface.AddSadComments(like, user_id, comId);
	}
	@PostMapping("/AddangryComments/{userId}/{comId}")
	public void AddangryCom(@PathVariable("userId")Long user_id,@PathVariable("comId")Long comId, @RequestBody LikeComments like){
		commentaireInterface.AddAngryComments(like, user_id, comId);
	}
}
