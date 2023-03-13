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

@Repository
public interface CommandeRepository extends JpaRepository<Commandes, Long> {
    List<Commandes> findByPaimentChoice(PaimentChoise paimentChoice);
    List<Commandes> findByDate(Date date);
    List<Commandes> findByDeliveryLocation(String deliveryLocation);



    List<Commandes> findById(String idCommandes);
}
