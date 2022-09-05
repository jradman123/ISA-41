package com.example.demo.dto;

public class CategoryDto {
    String name;
    String max;
    String min;
    String discount;

    public CategoryDto() {
    }

    public CategoryDto(String name, String max, String min, String discount) {
        this.name = name;
        this.max = max;
        this.min = min;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }
}
