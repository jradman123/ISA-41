package com.example.demo.controller;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.CreateAppointmentDto;
import com.example.demo.model.reservation.Appointment;
import com.example.demo.service.impl.AppointmentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentServiceImpl appointmentService;



    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('Admin')")
    @GetMapping(value="/getAppByCottage/{id}")
    public List<AppointmentDto> getAllByCottage(@PathVariable Long id) {
       return this.appointmentService.findApp(id);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser') || hasAuthority('Admin')")
    @GetMapping(value="/getAppByShip/{id}")
    public List<AppointmentDto> getAllByShip(@PathVariable Long id) {
        return this.appointmentService.findAppbyShip(id);
    }


    @DeleteMapping(value="/deleteApp/{id}")
    public ResponseEntity<Long> deleteApp(@PathVariable Long id) {
        return this.appointmentService.deleteApp(id);
    }




    @PostMapping(value="/createApp")
    public Appointment createApp(@RequestBody CreateAppointmentDto dto) {
        return this.appointmentService.createAppointment(dto);
    }

}
