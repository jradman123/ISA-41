package com.example.demo.model.ships;

import com.example.demo.model.cottages.Cottage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shipAvailability")
public class ShipAvailability {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    private Ship ship;


    public ShipAvailability(LocalDateTime startDate, LocalDateTime endDate, Ship ship) {
        this.id = id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.ship=ship;
    }
}
