package com.example.demo.service.impl;

import com.example.demo.dto.ReportDto;
import com.example.demo.model.Report;
import com.example.demo.model.Rules;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.Room;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.repository.ReportRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReportService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Report createReport(ReportDto newReport) {

        Report report=new Report();

        Reservation reservation= reservationRepository.findById(Long.parseLong(newReport.getReservationId())).orElse(null);
        report.setComment(newReport.getComment());
        report.setDate(LocalDateTime.now());
        report.setReservation(reservation);
        System.out.print("Izvjestaaaaaaaj"+newReport.getAppeared());
         report.setApprovedbyAdmin(false);
         report.setAppeared(false);
         report.setSanctioned(false);

        this.reportRepository.save(report);
        return report;
    }
}
