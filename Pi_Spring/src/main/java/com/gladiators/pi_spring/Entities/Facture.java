package com.gladiators.pi_spring.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Facture implements Serializable {

    @OneToOne
    @JoinColumn(name = "IdCommandes")
    private Commandes commandes;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String IdUser;

    private  Float TotalPrice;
}
