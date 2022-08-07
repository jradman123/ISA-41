package com.example.demo.repository;

import com.example.demo.model.cottages.CottageAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageAvailabilityRepository  extends JpaRepository<CottageAvailability, Long> {

}
