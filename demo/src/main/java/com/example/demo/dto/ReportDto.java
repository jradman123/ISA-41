package com.example.demo.dto;

import com.example.demo.model.Report;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {

    public String id;
    public String reservationId;
    public String comment;
    public String appeared;
    public String sanctioned;

    public ReportDto(Report report) {
        id = report.getId().toString();
        reservationId = report.getReservation().getId().toString();
        comment = report.getComment();
        if (report.isAppeared() == true) {
            appeared = "YES";
        }else {
            appeared="NO";
        }
        if (report.isSanctioned() == true) {
            sanctioned = "YES";
        }else {
            sanctioned="NO";
        }
    }
}
