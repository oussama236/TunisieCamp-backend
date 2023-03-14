package com.gladiators.pi_spring.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Evaluation {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column
    @Enumerated(EnumType.STRING)
    private typeEval noteValue;
    //@OneToOne
    //@JoinColumn(name = "activityEvalu")
//        @OneToOne(
//            mappedBy = "activityEvalu",
//            fetch = FetchType.LAZY
//    )

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activityEvalu")
    private Activity activityEvalu;


}
