package com.example.demo.dto;

import com.example.demo.model.reservation.Appointment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AppointmentDto {
    private String id;
    private String startDate;
    private String endDate;
    private String capacity;
    private String price;
    private String validUntil;
    private Boolean isReserved;
    private String cottageId;
    private String shipId;


    public AppointmentDto(Appointment a) {
        this.id = Long.toString(a.getId());
        this.startDate = a.getStartDate().toString();
        this.endDate = a.getEndDate().toString();
        this.capacity = Integer.toString(a.getCapacity());
        this.price = Double.toString(a.getPrice());
        this.validUntil=a.getValidUntil().toString();
        this.isReserved = a.getIsReserved();

        if (a.getCottage() != null) {
            this.cottageId = Long.toString(a.getCottage().getId());
        }
        if (a.getShip() != null) {
            this.shipId = Long.toString(a.getShip().getId());


        }

    }
}