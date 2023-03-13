package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Commentaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
	@Query(value="select mots from dictionnaire", nativeQuery=true)
	List<String> Dictionnaire();
}
