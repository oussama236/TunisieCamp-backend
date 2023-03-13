package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Outils;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface OutilRepository extends JpaRepository<Outils, Long> {


}
