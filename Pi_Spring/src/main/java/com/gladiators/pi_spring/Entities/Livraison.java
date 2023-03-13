package com.gladiators.pi_spring.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
public class Livraison implements Serializable {

    @OneToOne
    @JoinColumn(name = "IdCommandes")
    private Commandes commandes;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

   //  private String Id_Livreur;
     private  String Destination;
    private String emplacement;


}
