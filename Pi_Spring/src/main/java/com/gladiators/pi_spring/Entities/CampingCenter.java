package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CampingCenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idCentre;
    private String nomCentre ;
    private String centre_adresse ;
    @Enumerated(EnumType.STRING)
    private CentreZone centreZone;
    private Integer centre_price;
    private String centre_image;
    private boolean centre_disponibility;
    private Integer note;

    @JsonIgnore
    @OneToMany(mappedBy="camping_centre")
    private Set<Reservation> reservations;

    @JsonIgnore
    @OneToMany(mappedBy="camping_centre")
    private Set<Commentaire> commentaires;

}
