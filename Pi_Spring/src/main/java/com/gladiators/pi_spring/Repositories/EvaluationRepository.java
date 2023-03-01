package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {













//    @Query("Select e FROM Evaluation e join e.noteValue ")
//    List<Evaluation> Activty(@Param("activity") Activity activity);



//    @Query("SELECT noteValue FROM Evaluation  join Activity WHERE Activity .name")
//    List<Evaluation> Activty(@Param("activity") Activity activity);

//    @Query("SELECT e FROM Evaluation e  JOIN e.activity a WHERE a.id = :activityId")
//    List<Evaluation> findEvaluationByActivity(@Param("activityId") Long activityId);




    @Query("SELECT e FROM Activity e  WHERE e.activityEvalu = :noteValue")
    List<Evaluation> findEvaluationByActivity(@Param("noteValue") Long noteValue);
















































    //@Query("SELECT R FROM Evaluation R WHERE R.medecin.specialite =:specialite  )
    //public List<RendezVous> getEvaByCliniqueAndSpecialite(Long idClinique, Specialite specialite) ;



}
