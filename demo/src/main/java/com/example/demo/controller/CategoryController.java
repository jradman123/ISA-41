package com.example.demo.controller;

import com.example.demo.dto.CategoryResponse;
import com.example.demo.dto.CottageAvailabilityDto;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAuthority('Admin')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryResponse> response = new ArrayList<>();
        for (Category category : categoryService.findAll()) {
            response.add(new CategoryResponse(category));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
