package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.users.User;

import java.util.List;

public interface CategoryService {
    Category save(Category category);
    Category getCategoryForUser(String email);
    List<Category> findAll();
}
