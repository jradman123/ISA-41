package com.example.demo.service;

import com.example.demo.dto.CottageQuickReservationDto;
import com.example.demo.dto.CottageQuickReservationResponse;
import com.example.demo.model.cottages.CottageQuickReservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CottageQuickReservationService {
    List<CottageQuickReservationResponse> findApp(Long id);

    CottageQuickReservation createAppointment(CottageQuickReservationDto dto);

    ResponseEntity<Long> deleteApp(Long id);
}
