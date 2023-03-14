package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes,Long> {
}
