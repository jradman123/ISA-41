package com.example.demo.service.impl;

import com.example.demo.dto.ReservationDto;
import com.example.demo.service.ReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService
{
    @Override
    public List<ReservationDto> getReservationsByCottage(Long id) {
        return null;
    }

    @Override
    public List<ReservationDto> getReservationsByBoat(Long id) {
        return null;
    }
}
