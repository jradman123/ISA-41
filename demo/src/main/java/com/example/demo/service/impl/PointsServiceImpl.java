package com.example.demo.service.impl;

import com.example.demo.model.PointsForSuccessReservation;
import com.example.demo.repository.PointsForSuccessReservationRepository;
import com.example.demo.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointsServiceImpl implements PointsService {
    @Autowired
    private PointsForSuccessReservationRepository pointsForSuccessReservationRepository;

    @Override
    public PointsForSuccessReservation save(PointsForSuccessReservation points) {
        return pointsForSuccessReservationRepository.save(points);
    }
}
