package com.example.demo.model;

import javax.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="minPoints", nullable = false)
    private int minPoints;
    @Column(name="maxPoints", nullable = false)
    private int maxPoints;
    @Column(name="discount", nullable = false)
    private Double discount;

    public Category() {
    }

    public Category(Long id, String name, int minPoints, int maxPoints, Double discount) {
        this.id = id;
        this.name = name;
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public void setMinPoints(int minPoints) {
        this.minPoints = minPoints;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
