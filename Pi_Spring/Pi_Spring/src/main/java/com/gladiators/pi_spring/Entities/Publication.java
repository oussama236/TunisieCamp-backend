package com.gladiators.pi_spring.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pb;
    private String titre;
    private String contenu;
    @Lob
    private byte[] photo;
   
    @OneToMany(mappedBy = "publication",cascade = CascadeType.ALL)
    private Set<Commentaire> commentaires;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dislikes> dislikes = new ArrayList<>();
    @ManyToOne
    User user;
    
}
