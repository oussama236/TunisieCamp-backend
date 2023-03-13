package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    @Query(nativeQuery = true ,value = "SELECT * from User u  WHERE u.favourite_center like :keyword ")
    public List<User> recherche(@Param("keyword") String keyword);
}
