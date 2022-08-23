package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShipQuickReservationResponse {
    private String id;
    private String startTime;
    private String endTime;
    private String validUntil;
    private String price;
    private String shipId;
    private String guestLimit;
    private Set<ResponseUtility> utilities;
    private boolean isReserved;
}
