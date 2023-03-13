package com.gladiators.pi_spring.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Dislikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dis;

    @ManyToOne
    @JoinColumn(name = "id_pb")
    private Publication publication;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;
}
