package com.gladiators.pi_spring.Entities;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
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



    @Size(max = 255)
    @Column
    private String name;

    @Size(max = 255)
    @Column(name = "\"description\"")
    private String description;

    @Column
    private Integer capacity;

    @Column
    private Boolean disponibility;


    @Size(max = 255)
    @Column
    private String favourite;

    @Column
    private Date startTime;

    @Column
    private Date endTime;


//    @OneToOne
/*   @JoinColumn(name = "activity_evalu_id")
    @OneToOne
            (
            mappedBy = "activityEvalu"
            //,            fetch = FetchType.LAZY
    )
    private Evaluation activityEvalu;  */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activity_user_id")
    private User User22;

    @JsonIgnore
    @OneToMany(mappedBy = "activityEvalu")

    private Set<Evaluation> activityEvalu;

    @OneToMany(mappedBy = "sugACti")

    private Set<Suggestion> sugACtiSuggestions;


}

