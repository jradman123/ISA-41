package com.example.demo.controller;

import com.example.demo.dto.CottageDto;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.service.impl.CottageServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cottages")
public class CottageController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CottageServiceImpl cottageService;


    @GetMapping()
    public List<CottageDto> get() {
        return this.cottageService.findAll();
    }

}
