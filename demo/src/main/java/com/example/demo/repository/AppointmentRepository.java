package com.example.demo.repository;

import com.example.demo.model.cottages.CottageImage;
import com.example.demo.model.reservation.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository  extends JpaRepository<Appointment, Long> {
}
