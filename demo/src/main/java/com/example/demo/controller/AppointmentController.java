package com.example.demo.controller;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.service.impl.AppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;



    //idCottage or idBoat
    @GetMapping(value="/getApp/{id}")
    public List<AppointmentDto> getAllByCottage(@PathVariable Long id) {
       return this.appointmentService.findApp(id);
    }

    @DeleteMapping(value="/deleteApp/{id}")
    public ResponseEntity<Long> deleteApp(@PathVariable Long id) {
        return this.appointmentService.deleteApp(id);
    }

    @GetMapping(value = "/search")
    public List<AppointmentDto> search(AppointmentDto dto) {
        return this.appointmentService.search(dto);
    }


    @PostMapping(value="/createApp")
    public Appointment createApp(@RequestBody AppointmentDto dto) {
        return this.appointmentService.createAppointment(dto);
    }

}
