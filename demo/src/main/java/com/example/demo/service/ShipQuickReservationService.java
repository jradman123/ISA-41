package com.example.demo.service;

import com.example.demo.dto.CottageQuickReservationDto;
import com.example.demo.dto.ShipQuickReservationDto;
import com.example.demo.dto.ShipQuickReservationResponse;
import com.example.demo.model.ships.ShipQuickReservation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShipQuickReservationService {

    ShipQuickReservation createAppointment(ShipQuickReservationDto dto);

    ResponseEntity<Long> deleteApp(Long id);

    List<ShipQuickReservationResponse> findApp(Long id);
}
