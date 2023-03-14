package com.gladiators.pi_spring.Services.Interfaces;

import com.gladiators.pi_spring.Entities.Evaluation;

import java.util.List;

public interface IEvaluationService {




    Long AddEvaluation(Evaluation AC);
    void deleteEvaluationById(Long idA);

    public Evaluation updateEvaluation(Evaluation evaluation , long Activityid);

    List<Evaluation> retrieveAllEvaluation();
    public void assignEvaToActivity(Evaluation evaluationId, Long ActivityId);

public String mostEvaluation();
    public List<Evaluation> listeDeEvaluationParActivty(Long ActivityId);
}
