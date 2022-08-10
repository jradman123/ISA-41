package com.example.demo.repository;

import com.example.demo.model.adventures.AdventureUtility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureUtilityRepository extends JpaRepository<AdventureUtility, Long> {
}
