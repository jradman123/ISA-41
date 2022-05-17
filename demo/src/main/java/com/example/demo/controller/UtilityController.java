package com.example.demo.controller;

import com.example.demo.dto.RuleDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.service.impl.UtilityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utilities")
public class UtilityController {
    @Autowired
    private UtilityServiceImpl utilityService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/findUtilitiesByCottage/{id}")
    public List<UtilityDto> getUtilitesByCottage(@PathVariable Long id) {


        return utilityService.getUtilityByCottage(id);
    }


}
