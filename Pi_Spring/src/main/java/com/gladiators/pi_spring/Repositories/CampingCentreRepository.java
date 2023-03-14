package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.CampingCenter;
import com.gladiators.pi_spring.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories

public interface CampingCentreRepository extends JpaRepository<CampingCenter,Integer> {
    @Query(nativeQuery = true ,value = "SELECT * from camping_center s  WHERE s.nom_centre like :keyword ")
    public List<CampingCenter> recherche(@Param("keyword") String keyword);


}
