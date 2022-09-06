package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintDto;
import com.example.demo.model.Complaint;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.EmailSenderService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public List<ComplaintDto> getAllUnanswered() {
        List<ComplaintDto> complaintDtos = new ArrayList<>();
        for (Complaint complaint: complaintRepository.getAllUnanswered()) {
            complaintDtos.add(new ComplaintDto(complaint.getId().toString(),reservationService.getOwnersName(complaint.getReservation()),complaint.getComment()));
        }
        return complaintDtos;
    }

    @Transactional
    @Override
    public void answerOnComplaint(Long id,String answer) {
        Complaint complaint = complaintRepository.findComplaintById(id);
        complaint.setAnswered(true);
        complaintRepository.save(complaint);
        notifyOwnerAndClient(complaint.getReservation(),answer,complaint.getComment());
    }

    private void notifyOwnerAndClient(Reservation reservation,String answer,String complaint) {
        String client = reservation.getRegisteredUser().getEmail();
        String owner = reservationService.getOwnersEmail(reservation);
        emailSenderService.sendEmail(client,"Odgovor na žalbu!","Žalba : " + complaint + "\nOdgovor : " + answer);
        emailSenderService.sendEmail(owner,"Žalba!","Primili smo žalbu na Vaš račun.\nŽalba : " + complaint + "\nOdgovor : " + answer);
    }
}
