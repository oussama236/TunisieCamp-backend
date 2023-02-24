package com.gladiators.pi_spring.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Livraison {

    @OneToOne
    @JoinColumn(name = "IdCommandes")
    private Commandes commandes;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String IdLivraison;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private String IdLivreur;
     private  String Destination;


}
