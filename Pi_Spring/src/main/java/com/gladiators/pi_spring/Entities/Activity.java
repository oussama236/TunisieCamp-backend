package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter

public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column
    private String name;


    @Column(name = "\"description\"")
    private String description;

    @Column
    private Integer capacity;

    @Column
    private Boolean disponibility;


     @Column
    private String favourite;

    @Column
    private Date startTime;

    @Column
    private Date endTime;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_user_id")
    private User User22;

    @JsonIgnore
    @OneToMany(mappedBy = "activityEvalu")

    private Set<Evaluation> activityEvalu;

    @OneToMany(mappedBy = "sugACti")

    private Set<Suggestion> sugACtiSuggestions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activity")
    Set<ActivityLiked> activityLikes;

}

