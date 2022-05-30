package com.example.demo.dto;

import com.example.demo.model.cottages.CottageAvailability;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CottageAvailabilityDto {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String cottageId;

    public CottageAvailabilityDto(CottageAvailability ca) {
        this.startDate=ca.getStartDate();
        this.endDate=ca.getEndDate();
        this.cottageId=ca.getCottage().getId().toString();
    }
}
