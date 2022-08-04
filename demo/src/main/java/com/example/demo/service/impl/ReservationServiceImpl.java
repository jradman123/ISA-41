package com.example.demo.service.impl;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.ReservationDto;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService
{
    @Autowired
    private ReservationRepository reservationRepository;






    @Override
    public List<ReservationDto> getReservationsByCottage(Long id) {
        return null;
    }

    @Override
    public List<ReservationDto> getReservationsByBoat(Long id) {
        return null;
    }

    @Override
    public ReservationDto getById(Long id) {
        Reservation reservation= reservationRepository.findById(id).orElse(null);
        ReservationDto reservationDto=new ReservationDto(reservation);
        return reservationDto;
    }
}
