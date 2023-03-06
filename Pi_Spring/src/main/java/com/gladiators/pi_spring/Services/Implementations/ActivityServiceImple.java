package com.gladiators.pi_spring.Services.Implementations;

import com.gladiators.pi_spring.Entities.Activity;
import com.gladiators.pi_spring.Entities.ActivityLiked;
import com.gladiators.pi_spring.Entities.Evaluation;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.ActivityLikeRepository;
import com.gladiators.pi_spring.Repositories.ActivityRepository;
import com.gladiators.pi_spring.Repositories.EvaluationRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;

import com.gladiators.pi_spring.Services.Interfaces.IActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.sun.deploy.trace.Trace.println;

@Slf4j
@Service
public class ActivityServiceImple implements IActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ActivityLikeRepository activityLikeRepository;
    @Autowired
    EvaluationRepository evaluationRepository;

    //// hedhi tikhdem
//    @Override
//    public Activity AddActivityAndAffectUser(Activity A, Long Userid) {
//        User user = userRepository.findById(Userid).get ();
//        A.setUser22(user);
//        activityRepository.save (A);
//
//        return A;
//    }


    public Activity findById(Long id) {
        return activityRepository.findById (id)
                .orElseThrow (() -> new RuntimeException ("Activty Not Found"));

    }

    @Override
    public void assignUserAndEvaToActivity(Activity ActivityId, Long userId) {
        User user = userRepository.findById (userId).get ();
        //Evaluation evaluation = evaluationRepository.findById(EvaluationId).get ();
        ActivityId.setUser22 (user);
        //ActivityId.setActivityEvalu (evaluation);
        activityRepository.save (ActivityId);


    }


    public Integer nbActivityParUtilisateur(Activity n) {
        return activityRepository.nbActivityParUtilisateur (n);
    }


    @Override
    public List<Activity> recherche(String keyword) {

        if (keyword != null) {
            return activityRepository.recherche (keyword);
        } else return activityRepository.findAll ();
    }


    @Override
    public List<Evaluation> getByEvalyation(Long idActivity) {
        Activity a = activityRepository.findById (idActivity).orElse (null);
        List<Evaluation> evaluations = new ArrayList<> ();
        for (Evaluation e : evaluationRepository.findAll ()) {
            if (e.getActivityEvalu ().getId () == idActivity) {

                evaluations.add (e);
            }
        }

        return evaluations;
    }
//    @Transactional
//    @Override
//    public void assignUserAndEvaToActivity(Long ActivityId, Long userId) {
// Activity activity = activityRepository.findById(ActivityId).orElse(null);
////        User u = userRepository.findById(userId).orElse(null);
////        u.getActivityUserActivitys ().add(p);
//
//
//        User user = userRepository.findById(userId).orElse(null);
//
//        if (activity != null && user != null) {
//            user.getActivityUserActivitys ().add(activity);
//            userRepository.save(user);
//        } else {
//            log.info("najmouch");
//        }
//    }

//    @Override
//    public Long AddActivity(Activity AC) {
//        activityRepository.save (AC);
//        return AC.getId ();
//    }

    //
    @Override
    public void deleteActivityById(Long idA) {
        activityRepository.deleteById (idA);
    }


    public Activity updateActivity(Activity activity, Long userId) {
        Activity a1 = activityRepository.findById (userId).orElseThrow (() -> new EntityNotFoundException ("adversting not found"));
        a1.setName (activity.getName ());
        a1.setDescription (activity.getDescription ());
        a1.setCapacity (activity.getCapacity ());
        a1.setDisponibility (activity.getDisponibility ());
        a1.setFavourite (activity.getFavourite ());
        a1.setStartTime (activity.getStartTime ());
        a1.setEndTime (activity.getEndTime ());

        return activityRepository.saveAndFlush (a1);
    }


    @Override
    public List<Activity> retrieveAllActivity() {
        List<Activity> ListActivty = activityRepository.findAll ();
        return ListActivty;
    }


    @Override
    public List<Activity> getActivityByNameAndDesc(String name, String description) {
        List<Activity> ListActivty = activityRepository.findByNameAndDescription (name, description);
        return ListActivty;

    }

    @Override
    public ActivityLiked addLikeToActivity(ActivityLiked activityLiked, Long idActivity, Long userId) {
        int x = 0;
        boolean y = false;
        System.out.println("start methode");
        Activity activity = activityRepository.findById(idActivity).orElse(null);
        User user = userRepository.findById(userId).orElse(null);
        for (ActivityLiked activityLiked1 : activityLikeRepository.findAll()){
            System.out.println("start for");
            if (activityLiked1.getActivity().getId () == idActivity && activityLiked1.getUser().getId () == userId) {
                System.out.println("start if1");
                x = 1;
                y = activityLiked1.getIsLiked();
                activityLikeRepository.delete(activityLiked1);
            }
        }
      if (x == 0 || (x == 1 && y != activityLiked.getIsLiked())) {
            activityLiked.setUser(user);
            activityLiked.setActivity(activity);
            activityLikeRepository.save(activityLiked);
        }
        return activityLiked;
    }

    @Override
    public ResponseEntity<?> Delete_Like(Long idLike, Long idUser) {
        if (activityLikeRepository.existsById(idLike)) {
            ActivityLiked postLike = activityLikeRepository.findById(idLike)
                    .orElseThrow(() -> new EntityNotFoundException("Like Not Found"));
            User user = userRepository.findById(idUser).orElseThrow(() -> new EntityNotFoundException("User not found"));
            if (postLike.getUser().equals(user)) {
                activityLikeRepository.delete(postLike);
                return ResponseEntity.ok().body("Delete success");
            } else {
                return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body("No permission to delete this post");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Like Not Found");
        }
    }

//    @Override
//    public void deleActivityRecent(Activity ac, long idUser) {
//        Activity a1 = activityRepository.findById (idUser).orElseThrow (() -> new EntityNotFoundException ("adversting not found"));
//
//        List<Activity> activitesEnRetard = activityRepository.findByDateFinBefore(LocalDate.now());
//
//        for (Activity activite : activitesEnRetard) {
//            activityRepository.delete(activite);
//        }
//        List<Activity> allActivites = activityRepository.findAll();
//        System.out.println(allActivites);
//
//
//
//
//    }

   }











