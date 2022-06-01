package com.example.demo.model.cottages;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "cottageAvailability")
public class CottageAvailability {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;


    @Column(name = "startDate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "endDate", nullable = false)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "cottage_id", nullable = false)

    private Cottage cottage;


    public CottageAvailability(LocalDateTime startDate, LocalDateTime endDate, Cottage cottage) {
        this.id = id;
        this.startDate=startDate;
        this.endDate=endDate;
        this.cottage=cottage;
    }
}


