package com.gladiators.pi_spring.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_me;
    @ManyToOne
    private User transmetteur;
    @ManyToOne
    private User recepteur;
    private String contenu_m;
    @ManyToOne
    private Groupez group;
}
