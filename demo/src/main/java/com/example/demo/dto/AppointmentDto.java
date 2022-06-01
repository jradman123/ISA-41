package com.example.demo.dto;

import com.example.demo.model.AdditionalService;
import com.example.demo.model.reservation.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AppointmentDto {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer capacity;
    private Double price;
    private Boolean isReserved;
    private Long cottageId;
    private Long shipId;


    public AppointmentDto(Appointment a) {
        this.id = a.getId();
        this.startDate = a.getStartDate();
        this.endDate = a.getEndDate();
        this.capacity = a.getCapacity();
        this.price = a.getPrice();
        this.isReserved = a.getIsReserved();
        this.cottageId=a.getCottage().getId();
        this.shipId=a.getShip().getId();


    }

}