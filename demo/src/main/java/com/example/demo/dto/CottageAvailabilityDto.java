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

    public String id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long cottageId;

    public CottageAvailabilityDto(CottageAvailability ca) {
        this.id = Long.toString(ca.getId());
        this.startDate=ca.getStartDate();
        this.endDate=ca.getEndDate();
        this.cottageId=ca.getCottage().getId();
    }
}
