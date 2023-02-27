package com.gladiators.pi_spring.ServiceImp;

import com.gladiators.pi_spring.Entities.Activity;
import com.gladiators.pi_spring.Entities.Evaluation;
import com.gladiators.pi_spring.Entities.User;
import com.gladiators.pi_spring.Entities.typeEval;
import com.gladiators.pi_spring.Repositories.ActivityRepository;
import com.gladiators.pi_spring.Repositories.EvaluationRepository;
import com.gladiators.pi_spring.ServiceInter.IEvaluationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class EvaluationServiceImple implements IEvaluationService {



    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    ActivityRepository activityRepository;
    @Override
    public Long AddEvaluation(Evaluation EV) {
        evaluationRepository.save (EV);
        return EV.getId ();
    }

    @Override
    public void deleteEvaluationById(Long idA) {
        evaluationRepository.deleteById (idA);

    }

    @Override
    public Evaluation updateEvaluation(Evaluation evaluation) {
        evaluationRepository.save(evaluation);
        return evaluation;
    }

    @Override
    public List<Evaluation> retrieveAllEvaluation() {
        List<Evaluation> ListEvaluation = evaluationRepository.findAll();
        return ListEvaluation;
    }




    @Override
    public List<Evaluation> listeDeEvaluationParActivty(Long ActivityId) {


return  evaluationRepository.findEvaluationByActivity (ActivityId);
    }



    @Override
    public void assignEvaToActivity(Evaluation evaluationId, Long ActivityId) {
        Activity activity  = activityRepository.findById(ActivityId).get ();
        evaluationId.setActivityEvalu (activity);
        evaluationRepository.save (evaluationId);




    }

    @Override
    public String mostEvaluation() {
        int x=0;
                int y=0;
                int z=0;
                for (Evaluation e : evaluationRepository.findAll ()){
                  if (e.getNoteValue ().equals (typeEval.Bad)){
                      x++;
                  }
                  if (e.getNoteValue ().equals (typeEval.Good)){
                      y++;
                  }
                    if (e.getNoteValue ().equals (typeEval.Moyen)){
                        z++;
                    }
                }
                if (x>y && x>z){
                  return "Oups les evaluation noté bad est beaucoup";
                }
        if (y>x && y>z){
            return "Félicatation les evaluation noté Good est beaucoup";
        }
        else{
            return "les evaluation Moyen Good est beaucoup";
        }


    }


}
