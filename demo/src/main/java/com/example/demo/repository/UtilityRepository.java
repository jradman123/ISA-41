package com.example.demo.repository;

import com.example.demo.model.Utility;
import com.example.demo.model.cottages.Cottage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilityRepository extends JpaRepository<Utility, Long> {
}
