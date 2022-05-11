package com.example.demo.controller;

import com.example.demo.dto.RuleDto;
import com.example.demo.service.impl.RuleServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rules")
public class RuleController {

    @Autowired
    private RuleServiceImpl ruleService;

   /* @GetMapping(value="/getRuleByCottage/{id}")
    public List<RuleDto> getRuleByCottage(@PathVariable Long id) {
        return ruleService.getRulesByCottage(id);
    }
    */

}
