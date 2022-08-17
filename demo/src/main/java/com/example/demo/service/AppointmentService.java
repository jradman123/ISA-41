package com.example.demo.service;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.CreateAppointmentDto;
import com.example.demo.model.reservation.Appointment;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    List<AppointmentDto> findApp(Long id);


    ResponseEntity<Long> deleteApp(Long id);

    List<AppointmentDto> search(AppointmentDto dto);

   Appointment createAppointment(CreateAppointmentDto dto);



    void notifyUser(String email, String name, LocalDateTime date);
}
