package com.example.demo.model;

import com.example.demo.model.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id", nullable = false)
    @JsonManagedReference
    private Reservation reservation;


    @Column(name = "mark", nullable = false)
    private int mark;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "approved", nullable = false)
    private boolean approved;

    @Column(name = "unapproved", nullable = false)
    private boolean unapproved;
}
