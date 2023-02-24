package com.gladiators.pi_spring.Repositories;

import com.gladiators.pi_spring.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
