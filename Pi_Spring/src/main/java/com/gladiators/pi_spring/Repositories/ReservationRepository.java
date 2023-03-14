package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.CentreZone;
import com.gladiators.pi_spring.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.util.List;


@EnableJpaRepositories

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

    @Query("SELECT COUNT(r) FROM Reservation  r WHERE r.camping_centre.centreZone = :centreZone")
    Integer nbResParZone(@Param("centreZone") CentreZone centreZone);


}
