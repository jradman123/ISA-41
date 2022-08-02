package com.example.demo.service.impl;


import com.example.demo.dto.AppointmentDto;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<AppointmentDto> findApp(Long id) {
        List<AppointmentDto> appointmentDtos = new ArrayList<>();
        for (Appointment appointment : appointmentRepository.findAll()) {
            if (appointment.getCottage() != null & appointment.isDeleted() == false) {
                if (id.equals(appointment.getCottage().getId())) {
                    appointmentDtos.add(new AppointmentDto(appointment));
                } else if (id.equals(appointment.getShip().getId())) {
                    appointmentDtos.add(new AppointmentDto(appointment));
                }
            }
        }
        return appointmentDtos;
    }

    @Override
    public ResponseEntity<Long> deleteApp(Long id) {
        List<Appointment> appointments=this.appointmentRepository.findAll();
        for (Appointment appointment: appointments)
        {
            if(appointment.getId()==id) {
                appointment.setDeleted(true);
                appointmentRepository.save(appointment);
            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Override
    public List<AppointmentDto> search(AppointmentDto dto) {
        return null;
    }

    @Override
    public Appointment createAppointment(AppointmentDto dto) {
        return null;
    }




}
