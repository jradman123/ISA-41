package com.example.demo.service.impl;

import com.example.demo.dto.DataForSendEmail;
import com.example.demo.dto.ReportDto;
import com.example.demo.model.Report;
import com.example.demo.model.Rules;
import com.example.demo.model.adventures.AdventureQuickReservation;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.cottages.Room;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.repository.*;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.ReportService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;

    @Autowired
    private ShipReservationRepository shipReservationRepository;

    @Autowired
    private CottageReservationRepository cottageReservationRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserRepository userRepository;

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
            report.setSanctioned(false);
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

    @Override
    public List<Report> getAllUnseen() {
        return reportRepository.findAllUnseen();
    }

    @Override
    public void approveReport(Long id) {
        Report report = reportRepository.findById(id).get();
        String type = reservationRepository.findById(report.getReservation().getId()).get().getType();
        if(type.equals("ADVENTURE")){
            AdventureReservation res = adventureReservationRepository.findById(report.getReservation().getId()).get();
            String instructorEmail = userRepository.findById(res.getInstructorId()).get().getEmail();
            if(report.isSanctioned() && report.isAppeared()){
                sendIfSanction(new DataForSendEmail(res.getRegisteredUser().getEmail(),instructorEmail,
                        res.getReservationStart(),res.getReservationEnd(),res.getRegisteredUser().getFullName()));
            }else if((!report.isAppeared() && report.isSanctioned()) || (!report.isAppeared() && !report.isSanctioned())) {
                sendIfNotAppeared(new DataForSendEmail(res.getRegisteredUser().getEmail(), instructorEmail,
                        res.getReservationStart(), res.getReservationEnd(), res.getRegisteredUser().getFullName()));
            }
        }else if(type.equals("COTTAGE")){
            CottageReservation res = cottageReservationRepository.findById(report.getReservation().getId()).get();
            if(report.isSanctioned() && report.isAppeared()){
                sendIfSanction(new DataForSendEmail(res.getRegisteredUser().getEmail(),res.getCottage().getCottageOwner().getEmail(),
                        res.getReservationStart(),res.getReservationEnd(),res.getRegisteredUser().getFullName()));
            }else if((!report.isAppeared() && report.isSanctioned()) || (!report.isAppeared() && !report.isSanctioned())){
                sendIfNotAppeared(new DataForSendEmail(res.getRegisteredUser().getEmail(),res.getCottage().getCottageOwner().getEmail(),
                        res.getReservationStart(),res.getReservationEnd(),res.getRegisteredUser().getFullName()));
            }
        }else if(type.equals("SHIP")){
            ShipReservation res = shipReservationRepository.findById(Integer.parseInt(report.getReservation().getId().toString())).get();
            if(report.isSanctioned() && report.isAppeared()){
                sendIfSanction(new DataForSendEmail(res.getRegisteredUser().getEmail(),res.getShip().getShipOwner().getEmail(),
                                res.getReservationStart(),res.getReservationEnd(),res.getRegisteredUser().getFullName()));
            }else if((!report.isAppeared() && report.isSanctioned()) || (!report.isAppeared() && !report.isSanctioned())){
                sendIfNotAppeared(new DataForSendEmail(res.getRegisteredUser().getEmail(),res.getShip().getShipOwner().getEmail(),
                        res.getReservationStart(),res.getReservationEnd(),res.getRegisteredUser().getFullName()));
            }
        }
        report.setApprovedbyAdmin(true);
        reportRepository.save(report);
    }

    @Override
    public void rejectReport(Long id) {
        Report report = reportRepository.findById(id).get();
        report.setUnapprovedbyAdmin(true);
        reportRepository.save(report);
    }


    private void sendIfSanction(DataForSendEmail data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        emailSenderService.sendEmail(data.getClientEmail(),"Penal!","Zbog nedoličnog ponašanja" +
                " na terminu održanom od " + data.getStartTime().format(formatter) + " do " +
                data.getEndTime().format(formatter) + ",uprava našeg veb sajta je odlučila da vam dodijeli 1 penal.");

        emailSenderService.sendEmail(data.getOwnerEmail(),"Izvještaj prihvaćen!","Vaš zahtjev,da se" +
                " klijent  "  + data.getFullName() + " sankcioniše" + " zbog nedoličnog ponašanja na terminu održanom od " +
                data.getStartTime().format(formatter) + " do " + data.getEndTime().format(formatter) + ",je prihvaćen.");
    }

    private void sendIfNotAppeared(DataForSendEmail data){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        emailSenderService.sendEmail(data.getClientEmail(),"Penal!","Zbog nepojavljivanja " +
                " na terminu održanom od " + data.getStartTime().format(formatter) + " do " +
                data.getEndTime().format(formatter) + ",uprava našeg veb sajta je odlučila da vam dodijeli 1 penal.");

        emailSenderService.sendEmail(data.getOwnerEmail(),"Izvještaj prihvaćen!","Klijent " + data.getFullName() + " je dobio 1 penal zbog nepojavljivanja na terminu " +
                " održanom od " + data.getStartTime().format(formatter) + " do " + data.getEndTime().format(formatter) + ".");
    }
}
