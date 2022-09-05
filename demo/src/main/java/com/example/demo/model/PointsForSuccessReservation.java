package com.example.demo.model;

import javax.persistence.*;

@Entity(name="points")
public class PointsForSuccessReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="client",nullable = false)
    private int client;
    @Column(name="owner", nullable = false)
    private int owner;
    @Column(name="keepsApp", nullable = false)
    private Double keepsApp;

    public PointsForSuccessReservation() {
    }

    public PointsForSuccessReservation(Long id, int client, int owner, Double keepsApp) {
        this.id = id;
        this.client = client;
        this.owner = owner;
        this.keepsApp = keepsApp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public Double getKeepsApp() {
        return keepsApp;
    }

    public void setKeepsApp(Double keepsApp) {
        this.keepsApp = keepsApp;
    }
}
