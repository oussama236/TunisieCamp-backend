package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Controllers.CommentaireController;
import com.gladiators.pi_spring.Entities.*;
import com.gladiators.pi_spring.Repositories.CommentaireRepository;
import com.gladiators.pi_spring.Repositories.LikeCommentsRepository;
import com.gladiators.pi_spring.Repositories.PublicationRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.Services.Interfaces.CommentaireInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CommentaireImp implements CommentaireInterface {
    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    LikeCommentsRepository likeCommentsRepository;
   
    @Override
    public Commentaire addCommentaire(Commentaire commentaire){
        commentaireRepository.save(commentaire);
        return commentaire;
    }
    @Override
    public Commentaire updateCommentaire(Commentaire commentaire) {
    commentaireRepository.save(commentaire);
    return commentaire;
    }

    @Override
    public void deleteCommentaire(Long id_cm){
        commentaireRepository.deleteById(id_cm);
    }
    @Override
    public void affectCommentaireToPublication(Long id_cm,Long id_pb){
        Commentaire c = commentaireRepository.findById(id_cm).get();
        Publication p = publicationRepository.findById(id_pb).get();
        p.getCommentaires().add(c);
        commentaireRepository.save(c);
    }
    @Override
    public List<Commentaire> retrieveCommentairesPublicationID(Long id_pb){
        Publication publication=publicationRepository.findById(id_pb).get();
        List<Commentaire> commentaireList = (List<Commentaire>) publication.getCommentaires();
        return commentaireList;
    }
	@Override
	public Commentaire AddComments(Long user_id, Commentaire com, Long pub_id) {
		userRepository.findById(user_id).map(user -> {
			com.setUser(user);
			return commentaireRepository.save(com);
		}).get();
		return publicationRepository.findById(pub_id).map(pub -> {
			com.setPublication(pub);
			return commentaireRepository.save(com);
		}).get();
	}
//	@Override
//	public void sendMail(Commentaire comment, Long idUser, Long idPost) {
//		User user =userRepository.findById(idUser).orElse(null);
//		Publication publication = publicationRepository.findById(idPost).orElse(null);
//		this.AddComments(idUser, comment, idPost);
//    	String body = user.getFirstName()+" a commenté votre publication "+publication.getTitre();
//		
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(publication.getUser().getEmail());
//		mail.setSubject("Nouveau commentaire sur votre post");
//		mail.setText(body);
//		javaMailSender.send(mail);
//		
//	}
@Override
public void AddLikesComments(LikeComments like_com, Long user_id, Long com_id) {

    Iterable<User> user = userRepository.findAll();
    for (User user2 : user) {
        if (likeCommentsRepository.LikesComments(com_id, user_id)==0) {
            if (user2.getId()==user_id) {

                commentaireRepository.findById((long) com_id).map(c ->{
                    like_com.setReactComments(ReactComments.Like);
                    like_com.setUser(user2);
                    like_com.setPub(c);
                    like_com.setLiked(true);
                    return c;

                });

                likeCommentsRepository.save(like_com);
            }

        }

    }

}

    @Override
    public void AddloveComments(LikeComments like_com, Long user_id, Long com_id) {
        Iterable<User> user = userRepository.findAll();
        for (User user2 : user) {
            if (likeCommentsRepository.LoveComments(com_id, user_id)==0) {
                if (user2.getId()==user_id) {

                    commentaireRepository.findById((long) com_id).map(c ->{
                        like_com.setReactComments(ReactComments.love);
                        like_com.setUser(user2);
                        like_com.setPub(c);
                        like_com.setLiked(true);
                        return c;

                    });

                    likeCommentsRepository.save(like_com);
                }

            }

        }

    }

    @Override
    public void AddHahaComments(LikeComments like_com, Long user_id, Long com_id) {
        Iterable<User> user = userRepository.findAll();
        for (User user2 : user) {
            if (likeCommentsRepository.HahaComments(com_id, user_id)==0) {
                if (user2.getId()==user_id) {

                    commentaireRepository.findById((long) com_id).map(c ->{
                        like_com.setReactComments(ReactComments.haha);
                        like_com.setUser(user2);
                        like_com.setPub(c);
                        like_com.setLiked(true);
                        return c;

                    });

                    likeCommentsRepository.save(like_com);
                }

            }

        }
    }

    @Override
    public void AddSadComments(LikeComments like_com, Long user_id, Long com_id) {
        Iterable<User> user = userRepository.findAll();
        for (User user2 : user) {
            if (likeCommentsRepository.SadComments(com_id, user_id)==0) {
                if (user2.getId()==user_id) {

                    commentaireRepository.findById((long) com_id).map(c ->{
                        like_com.setReactComments(ReactComments.sad);
                        like_com.setUser(user2);
                        like_com.setPub(c);
                        like_com.setLiked(true);
                        return c;

                    });

                    likeCommentsRepository.save(like_com);
                }

            }

        }

    }

    @Override
    public void AddAngryComments(LikeComments like_com, Long user_id, Long com_id) {
        Iterable<User> user = userRepository.findAll();
        for (User user2 : user) {
            if (likeCommentsRepository.AngryComments(com_id, user_id)==0) {
                if (user2.getId()==user_id) {

                    commentaireRepository.findById((long) com_id).map(c ->{
                        like_com.setReactComments(ReactComments.angry  );
                        like_com.setUser(user2);
                        like_com.setPub(c);
                        like_com.setLiked(true);
                        return c;

                    });

                    likeCommentsRepository.save(like_com);
                }

            }

        }

    }

}


