package com.example.demo.repository;

import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.cottages.CottageImage;
import com.example.demo.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CottageAvailabilityRepository  extends JpaRepository<CottageAvailability, Long> {

}
