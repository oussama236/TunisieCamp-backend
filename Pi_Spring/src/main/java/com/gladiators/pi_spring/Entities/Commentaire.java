package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Commentaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idCommentaire;
    private Date DateCommentaire;
    private String Sujet ;

    @JsonIgnore
    @ManyToOne
    private CampingCenter camping_centre;

    @ManyToOne
    @JoinColumn(name = "commentaires")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commentaire")
    Set<Likes> likes;
}
