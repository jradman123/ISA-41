package com.example.demo.service.impl;

import com.example.demo.dto.ComplaintDto;
import com.example.demo.model.Complaint;
import com.example.demo.repository.ComplaintRepository;
import com.example.demo.service.ComplaintService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private ReservationService reservationService;

    @Override
    public List<ComplaintDto> getAllUnanswered() {
        List<ComplaintDto> complaintDtos = new ArrayList<>();
        for (Complaint complaint: complaintRepository.getAllUnanswered()) {
            complaintDtos.add(new ComplaintDto(complaint.getId().toString(),reservationService.getOwnersName(complaint.getReservation()),complaint.getComment()));
        }
        return complaintDtos;
    }
}
