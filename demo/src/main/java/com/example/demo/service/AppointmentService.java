package com.example.demo.service;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.model.reservation.Appointment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> findApp(Long id);


    ResponseEntity<Long> deleteApp(Long id);

    List<AppointmentDto> search(AppointmentDto dto);

   Appointment createAppointment(AppointmentDto dto);
}
