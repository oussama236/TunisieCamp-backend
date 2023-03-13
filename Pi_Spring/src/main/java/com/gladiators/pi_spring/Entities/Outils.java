package com.gladiators.pi_spring.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "outils")
public class Outils implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    private User User;

    @Column(name = "nombreVisites")
    private int nombreVisites;

    @ManyToMany(mappedBy = "outils")
    private Set<Commandes> commandes = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private  String Name;

    private Cathegorie Cathegorie;

    private  Float Price;

    private  Float Poids;
    private String image;





}
