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
import java.util.List;


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
        reservation.setHaveReport(true);
        this.reservationRepository.save(reservation);

        if(newReport.getAppeared().equals("1")) {
            report.setAppeared(true);

        }else {
            report.setAppeared(false);
        }

        if(newReport.getSanctioned().equals("1")) {
            report.setSanctioned(true);}
        else {
            report.setAppeared(false);
        }

         report.setApprovedbyAdmin(false);


        this.reportRepository.save(report);

        return report;

    }

    @Override
    public Report findReportbyResId(Long id) {
        List<Report> reports=reportRepository.findAll();
        for(Report report:reports) {
            if(report.getReservation().getId()==id) {
                return report;

            }
        }
        return null;


    }
}
