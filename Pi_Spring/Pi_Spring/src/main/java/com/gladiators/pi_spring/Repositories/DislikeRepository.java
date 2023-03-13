package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Dislikes;
import com.gladiators.pi_spring.Entities.Likes;
import com.gladiators.pi_spring.Entities.Publication;
import com.gladiators.pi_spring.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface DislikeRepository extends JpaRepository<Dislikes,Long> {
    public Dislikes findDislikesByUserAndPublication (@Param("user") User user, @Param("publication") Publication publication);
}
