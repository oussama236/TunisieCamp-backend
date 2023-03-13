package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long roleId;

    @Enumerated(EnumType.STRING)
    @Column
    RoleName roleName;


    @ManyToMany( cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users ;

}
