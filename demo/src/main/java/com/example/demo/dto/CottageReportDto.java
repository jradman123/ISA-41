package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CottageReportDto {

    public Double cottageMark;
    public String reservationPerYear;
    public String reservationPerWeek;
    public String reservationPerMonth;


}
