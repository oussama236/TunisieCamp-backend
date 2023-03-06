package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity,Long> {


//    List<Activity> findByDateFinBefore(LocalDate endTime);


    @Query(nativeQuery = true ,value = "SELECT * from Activity s  WHERE s.name like :keyword ")
    public List<Activity> recherche(@Param("keyword") String keyword);























    // Récupère toutes les activités dont la date de début est comprise entre deux dates données
   // List<Activity> findActivitiesByStartDateBetween(Date start, Date end);

    // Récupère le nombre total d'activités d'un certain type
   // long countActivitiesByType(String type);





            @Query("SELECT a FROM Activity a WHERE a.startTime BETWEEN :startDate AND :endDate and a.User22.id=:id")
    List<Activity> findActivitiesByStartDateBetween(@Param("start") Date start, @Param("end") Date end);




    @Query("SELECT COUNT(u) FROM User u WHERE u.activityUserActivitys = :user")
    Integer nbActivityParUtilisateur(@Param("user") Activity activity);



//recherche
    @Query("select t FROM Activity t where t.name= ?1 and t.description = ?2 ")
    List<Activity> findByNameAndDescription(String name , String description);


}
