package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLike;
    Date likedTime;

    Boolean isLiked ;


    @JsonIgnore
    @ManyToOne
    User user;

    @JsonIgnore
    @ManyToOne
    Commentaire commentaire;

}
