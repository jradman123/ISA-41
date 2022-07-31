package com.example.demo.service.impl;


import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.RuleDto;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentServiceImpl appointmentService;


    @Autowired
    private AppointmentRepository appointmentRepository;


    @Override
    public List<AppointmentDto> findAppByCottage(Long id) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getCottage() != null & appointment.isDeleted() == false) {
                if (id.equals(appointment.getCottage().getId())) {
                    appointmentDtos.add(new AppointmentDto(appointment));

                }
            }
        }
        return appointmentDtos;
    }
}
