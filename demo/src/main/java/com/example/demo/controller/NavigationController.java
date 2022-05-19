package com.example.demo.controller;

import com.example.demo.dto.NavigationDto;
import com.example.demo.dto.UtilityDto;
import com.example.demo.service.NavigationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/navigation")
public class NavigationController {

    @Autowired
    private NavigationService navigationService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value="/findNavigationbyBoat/{id}")
    public List<NavigationDto> findNavigationbyBoat(@PathVariable Long id) {


        return navigationService.findNavigationbyBoat(id);
    }
}
