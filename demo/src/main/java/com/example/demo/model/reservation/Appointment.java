package com.example.demo.model.reservation;

import com.example.demo.model.AdditionalService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "price", nullable = false)
    private Integer price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "appointment_additional_service",
            joinColumns = {@JoinColumn(name = "appointment_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    private List<AdditionalService> additionalServices;


    @Column(name = "isReserved", nullable = false)
    private Boolean isReserved;

    @Column(name = "isDeleted", nullable = false)
    private boolean isDeleted;
}
