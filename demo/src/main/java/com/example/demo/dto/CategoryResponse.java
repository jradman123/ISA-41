package com.example.demo.dto;

import com.example.demo.model.Category;

public class CategoryResponse {
    private String id;
    private String name;
    private String discount;
    private String min;
    private String max;

    public CategoryResponse() {
    }

    public CategoryResponse(String id, String name, String discount, String min, String max) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.min = min;
        this.max = max;
    }

    public CategoryResponse(Category category) {
        id = category.getId().toString();
        name = category.getName();
        discount = category.getDiscount().toString();
        min = String.valueOf(category.getMinPoints());
        max=String.valueOf(category.getMaxPoints());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
