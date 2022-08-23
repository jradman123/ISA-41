package com.example.demo.model.ships;

import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageUtility;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shipQuickReservation")
public class ShipQuickReservation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @NotNull
    private Double price;
    @NotNull
    private LocalDateTime validUntil;

    @ManyToOne()
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;

    private int guestLimit;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ship_quick_reservation_ship_utility",
            joinColumns = @JoinColumn(name = "ship_quick_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "ship_utility_id"))
    private Set<ShipUtility> shipUtilities;

    private boolean isReserved = false;
    private boolean isDeleted = false;

}