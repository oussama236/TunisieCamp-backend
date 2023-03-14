package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private String nomCentre;
    private Integer reservation_price;
    private Date DateReservation;

    @JsonIgnore
    @ManyToOne
    private CampingCenter camping_centre;

    @OneToMany(mappedBy = "reservation")
    private List<User> users;
}