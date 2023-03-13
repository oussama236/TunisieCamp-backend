package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Commandes;
import com.gladiators.pi_spring.Entities.PaimentChoise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commandes, Long> {
    List<Commandes> findByPaimentChoice(PaimentChoise paimentChoice);

    List<Commandes> findByDate(Date date);

    List<Commandes> findByDeliveryLocation(String deliveryLocation);

    @Modifying
    @Query("DELETE FROM Commandes c WHERE c.IdCommandes = :IdCommandes")
    void deleteById(@Param("IdCommandes") Long IdCommandes);

    List<Commandes> findById(String idCommandes);
}
