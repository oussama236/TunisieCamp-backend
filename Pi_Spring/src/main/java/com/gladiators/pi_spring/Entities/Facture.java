package com.gladiators.pi_spring.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Facture {

    @OneToOne
    @JoinColumn(name = "IdCommandes")
    private Commandes commandes;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String IdFacture;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private  String IdUser;

    private  Float TotalPrice;
}
