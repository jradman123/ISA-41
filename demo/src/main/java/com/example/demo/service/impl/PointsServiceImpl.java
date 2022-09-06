package com.example.demo.service.impl;

import com.example.demo.dto.PointsDto;
import com.example.demo.model.Category;
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

    @Override
    public PointsForSuccessReservation update(PointsDto pointsDto) {
        PointsForSuccessReservation points = pointsForSuccessReservationRepository.findById(Long.parseLong(pointsDto.getId())).get();
        points.setClient(Integer.parseInt(pointsDto.getClient()));
        points.setOwner(Integer.parseInt(pointsDto.getOwner()));
        points.setKeepsApp(Double.parseDouble(pointsDto.getKeepsApp()));
        return pointsForSuccessReservationRepository.save(points);
    }

    @Override
    public PointsForSuccessReservation get() {
        return pointsForSuccessReservationRepository.findAll().stream().findFirst().get();
    }
}
