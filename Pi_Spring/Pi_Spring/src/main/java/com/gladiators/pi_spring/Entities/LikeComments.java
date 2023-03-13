package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class LikeComments {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int Like_id;
    @Column
    private boolean isLiked;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="com_id")
    private Commentaire com;

    @Enumerated(EnumType.STRING)
    private ReactComments reactComments;
    @JsonIgnore
    public Commentaire getPub() {
        return com;
    }
    public void setPub(Commentaire com) {
        this.com = com;
    }
}
