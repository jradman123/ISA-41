package com.example.demo.service;

import com.example.demo.dto.RuleDto;

import java.util.List;

public interface RuleService {

    List<RuleDto> getRulesByCottage(Long id);
}
