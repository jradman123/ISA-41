package com.example.demo.dto;

import com.example.demo.model.PointsForSuccessReservation;

public class PointsDto {
    private String id;
    private String client;
    private String owner;
    private String keepsApp;

    public PointsDto() {
    }

    public PointsDto(String id, String client, String owner, String keepsApp) {
        this.id = id;
        this.client = client;
        this.owner = owner;
        this.keepsApp = keepsApp;
    }

    public PointsDto(PointsForSuccessReservation points){
        this.id = points.getId().toString();
        this.client = String.valueOf(points.getClient());
        this.keepsApp = points.getKeepsApp().toString();
        this.owner = String.valueOf(points.getOwner());

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getKeepsApp() {
        return keepsApp;
    }

    public void setKeepsApp(String keepsApp) {
        this.keepsApp = keepsApp;
    }
}
