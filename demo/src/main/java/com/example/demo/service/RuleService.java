package com.example.demo.service;

import com.example.demo.dto.RuleDto;
import com.example.demo.model.Rules;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RuleService {

    List<RuleDto> getRulesByCottage(Long id);
    ResponseEntity<Long> deleteRuleByCottage(Long id, Long idCottage);
}
