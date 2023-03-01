package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.Activity;
import com.gladiators.pi_spring.Entities.Evaluation;

import java.util.List;

public interface IActivityService {
//    public Activity AddActivityAndAffectUser(Activity A , Long Userid);

    public void assignUserAndEvaToActivity(Activity ActivityId, Long userId);
            //, Long EvaluationId


    List<Evaluation> getByEvalyation(Long idActivity);

    //    Long AddActivity(Activity AC);
    void deleteActivityById(Long idA);

    public Activity updateActivity(Activity activity, Long userId);
    public Activity findById(Long id);

    List<Activity> retrieveAllActivity();

    public Integer nbActivityParUtilisateur(Activity n);
public List<Activity> recherche(String keyword);
    public List<Activity> getActivityByNameAndDesc(String name , String description);
}
