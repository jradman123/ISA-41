package com.example.demo.model.adventures;

import javax.persistence.*;

@Entity
public class AdventureUtility {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @Column(name = "price", nullable = false)
    private Double price = 0.0;

    public AdventureUtility() {
    }

    public AdventureUtility(Long id, String name, boolean isDeleted, Double price) {
        this.id = id;
        this.name = name;
        this.isDeleted = isDeleted;
        this.price = price;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
