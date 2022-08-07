package com.example.demo.repository;

import com.example.demo.model.adventures.AdventureRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdventureRuleRepository extends JpaRepository<AdventureRule, Long> {
}
