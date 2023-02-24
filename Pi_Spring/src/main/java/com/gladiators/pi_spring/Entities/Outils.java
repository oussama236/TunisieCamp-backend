package com.gladiators.pi_spring.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "outils")
public class Outils {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User User;

    @Column(name = "nombreVisites")
    private int nombreVisites;

    @ManyToMany(mappedBy = "outils")
    private Set<Commandes> commandes = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long IdOutils;

    private  String Name;

    private Cathegorie Cathegorie;

    private  Float Price;

    private  Float Poids;




}
