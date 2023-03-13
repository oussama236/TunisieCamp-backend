package com.gladiators.pi_spring.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Commandes implements Serializable {

    @ManyToMany
    @JoinTable( name = "commande_outils", joinColumns = @JoinColumn(name = "IdCommandes"),
            inverseJoinColumns = @JoinColumn(name = "IdOutils"))
    private List<Outils> outils;


    @OneToOne(mappedBy = "commandes")
    private Facture facture;

    @OneToOne(mappedBy = "commandes")
    private Livraison livraison;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  long IdOutils;

    private Float price;

    @Enumerated(EnumType.STRING)
    private PaimentChoise paimentChoice;

    private String deliveryLocation;

    @Enumerated(EnumType.STRING)
    private MoyenTransport moyenTransport;

    private Date date;


    public void setId(Long idCommande) {
    }




}
