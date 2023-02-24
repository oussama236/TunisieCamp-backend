package com.gladiators.pi_spring.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Commandes {

    @ManyToMany
    @JoinTable( name = "commande_outils", joinColumns = @JoinColumn(name = "IdCommandes"),
            inverseJoinColumns = @JoinColumn(name = "IdOutils"))
    private Set<Outils> outils = new HashSet<>();


    @OneToOne(mappedBy = "commandes")
    private Facture facture;

    @OneToOne(mappedBy = "commandes")
    private Livraison livraison;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String IdCommandes;

    private String IdOutils;

    private Float Price;

    private  PaimentChoise PaimentChoice;

    private  String DeleveryLocation;

    private MoyenTransport MoyenTrasport;


    public void setIdCommandes(Long idCommande) {
    }


}
