package com.example.demo.service.impl;

import com.example.demo.dto.CategoryDto;
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
    public Category save(CategoryDto categoryDto) {
        Category category = new Category();
        category.setDiscount(Double.parseDouble(categoryDto.getDiscount()));
        category.setMaxPoints(Integer.parseInt(categoryDto.getMax()));
        category.setMinPoints(Integer.parseInt(categoryDto.getMin()));
        category.setName(categoryDto.getName());
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

    @Override
    public Category update(Long id,CategoryDto category) {
        Category categoryDb = categoryRepository.findById(id).get();
        categoryDb.setName(category.getName());
        categoryDb.setMinPoints(Integer.parseInt(category.getMin()));
        categoryDb.setMaxPoints(Integer.parseInt(category.getMax()));
        categoryDb.setDiscount(Double.parseDouble(category.getMax()));
        return categoryRepository.save(categoryDb);
    }

}
