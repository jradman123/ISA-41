package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.model.Category;
import com.example.demo.model.users.User;

import java.util.List;

public interface CategoryService {
    Category save(CategoryDto category);
    Category getCategoryForUser(String email);
    List<Category> findAll();
    Category update(Long id,CategoryDto category);
}
