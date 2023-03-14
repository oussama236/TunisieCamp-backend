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
public class ActivityLiked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idActivityLiked;
    Date likedAt;

    Boolean isLiked ;

    @JsonIgnore
    @ManyToOne
    User user; // The user who clicked Like
    @JsonIgnore
    @ManyToOne
    Activity activity; // The post to like



}
