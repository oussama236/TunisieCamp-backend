package com.gladiators.pi_spring.ServiceImp;

import com.gladiators.pi_spring.Entities.Activity;
import com.gladiators.pi_spring.Entities.Evaluation;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Repositories.ActivityRepository;
import com.gladiators.pi_spring.Repositories.EvaluationRepository;
import com.gladiators.pi_spring.Repositories.UserRepository;
import com.gladiators.pi_spring.ServiceInter.IActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ActivityServiceImple implements IActivityService {

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

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


    public Activity findById(Long id){
        return  activityRepository.findById (id)
                .orElseThrow (()-> new RuntimeException ("Activty Not Found"));

    }

    @Override
    public void assignUserAndEvaToActivity(Activity ActivityId, Long userId) {
        User user = userRepository.findById(userId).get ();
        //Evaluation evaluation = evaluationRepository.findById(EvaluationId).get ();
        ActivityId.setUser22(user);
        //ActivityId.setActivityEvalu (evaluation);
        activityRepository.save (ActivityId);




    }



    public Integer nbActivityParUtilisateur(Activity n)
    {
        return activityRepository.nbActivityParUtilisateur(n);
    }

    @Override
    public List<Activity> recherche(String keyword) {

        if (keyword != null) {
            return activityRepository.recherche (keyword);
        }
        else return activityRepository.findAll ();
    }

    @Override
    public List<Activity> getActivityByNameAndDesc(String name, String description) {
        List<Activity> ListActivty = activityRepository.findByNameAndDescription (name,description);
        return   ListActivty ;

    }
@Override
public List<Evaluation> getByEvalyation(Long idActivity){
        Activity a = activityRepository.findById (idActivity).orElse (null);
        List<Evaluation> evaluations = new ArrayList<> ();
        for (Evaluation e :evaluationRepository.findAll()){
            if (e.getActivityEvalu ().getId () == idActivity){

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

    @Override
    public Activity updateActivity(Activity activity, Long userId) {

        User user = userRepository.findById(userId).get ();
        //Evaluation evaluation = evaluationRepository.findById(EvaluationId).get ();
        activity.setUser22(user);
        return activityRepository.save(activity);
    }

    @Override
    public List<Activity> retrieveAllActivity() {
        List<Activity> ListActivty = activityRepository.findAll ();
        return ListActivty;
    }




//    public void export(HttpServletResponse response) throws IOException {
//        Document document = new Document(PageSize.A4);
//        PdfWriter.getInstance(document, response.getOutputStream());
//
//        document.open();
//        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
//        fontTitle.setSize(18);
//
//        Paragraph paragraph = new Paragraph("This is a title.", fontTitle);
//        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
//
//        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
//        fontParagraph.setSize(12);
//
//        Paragraph paragraph2 = new Paragraph("This is a paragraph.", fontParagraph);
//        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
//
//        document.add(paragraph);
//        document.add(paragraph2);
//        document.close();
//    }


}

