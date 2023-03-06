package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.ActivityLiked;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLikeRepository extends JpaRepository<ActivityLiked,Long> {
}
