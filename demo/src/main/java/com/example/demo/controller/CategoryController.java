package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.dto.CottageAvailabilityDto;
import com.example.demo.dto.PointsDto;
import com.example.demo.model.Category;
import com.example.demo.model.PointsForSuccessReservation;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PointsService;
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

    @Autowired
    private PointsService pointsService;

    @PreAuthorize("hasAuthority('Admin')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAll() {
        List<CategoryResponse> response = new ArrayList<>();
        for (Category category : categoryService.findAll()) {
            response.add(new CategoryResponse(category));
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/update/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,@RequestBody CategoryDto categoryDto) {
        Category saved = categoryService.update(id,categoryDto);
        if(saved == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<>(new CategoryResponse(saved), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('Admin')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping()
    public ResponseEntity<CategoryResponse> addNew(@RequestBody CategoryDto categoryDto) {
        Category saved = categoryService.save(categoryDto);
        if(saved == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<>(new CategoryResponse(saved), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAuthority('Admin')")
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/points")
    public ResponseEntity<PointsDto> getPoints() {
        return new ResponseEntity<>(new PointsDto(pointsService.get()), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Admin')")
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/update-points")
    public ResponseEntity<PointsDto> updatePoints(@RequestBody PointsDto pointsDto) {
        PointsForSuccessReservation saved = pointsService.update(pointsDto);
        if(saved == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            return new ResponseEntity<>(new PointsDto(saved), HttpStatus.OK);
        }
    }
}
