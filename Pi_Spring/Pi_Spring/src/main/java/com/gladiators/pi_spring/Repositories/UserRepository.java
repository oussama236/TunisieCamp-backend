package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
