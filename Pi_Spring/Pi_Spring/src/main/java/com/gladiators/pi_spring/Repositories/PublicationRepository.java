package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
}
