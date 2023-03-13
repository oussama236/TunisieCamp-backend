package com.gladiators.pi_spring.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class  Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cm;
    private String contenu_cm;
    private Date date_cm ;
    @ManyToOne
    private Publication publication;
    @ManyToOne
    private User user;
   
}
