package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeRepository extends JpaRepository<Commandes, Long> {

}
