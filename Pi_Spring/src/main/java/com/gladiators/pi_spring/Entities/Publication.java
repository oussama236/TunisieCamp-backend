package com.gladiators.pi_spring.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pb;
    private String contenu;

    @OneToMany(mappedBy = "publication",cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires;
}
