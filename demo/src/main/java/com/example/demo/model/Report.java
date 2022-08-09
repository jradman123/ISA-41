package com.example.demo.model;

import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.users.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "report")
public class Report {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    private User user;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "ReportDate", nullable = false)
    private LocalDateTime date;

    @Column(name = "typeOfReservation", nullable = false)
    private String typeOfReservation;

    @Column(name = "realization", nullable = false)
    private String realization;

    @Column(name = "entityName", nullable = false)
    private String entityName;

    @Column(name = "rating", nullable = false)
    private Integer rating;

}
