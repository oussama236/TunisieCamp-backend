package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Data
public class User {
    @Id
   // @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String picture;

    @Column
    private String favouriteCenter;

    @Column
    private Integer phoneNumber;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "users")
    @JsonIgnore
    private List<Role> roles ;
    @ManyToMany(cascade = CascadeType.ALL , mappedBy = "members")
    private Set<Groupez> groups;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Publication> publications ;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    List<Commentaire>commentaires;
}