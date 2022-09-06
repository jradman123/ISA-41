package com.example.demo.service;

import com.example.demo.dto.PointsDto;
import com.example.demo.model.PointsForSuccessReservation;

public interface PointsService {
    PointsForSuccessReservation save(PointsForSuccessReservation points);
    PointsForSuccessReservation update(PointsDto pointsDto);
    PointsForSuccessReservation get();
}
