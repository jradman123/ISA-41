package com.example.demo.service.impl;

import com.example.demo.model.Category;
import com.example.demo.model.users.User;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryForUser(String email) {
        int usersPoints = userService.findByEmail(email).getScoredPoints();
        for (Category category : categoryRepository.findAll()) {
            if(usersPoints>=category.getMinPoints() && category.getMaxPoints()>=usersPoints){
                return category;
            }
        }
        return null;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
