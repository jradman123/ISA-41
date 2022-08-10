package com.example.demo.dto;

public class AdventureUtilityDto {

    private String name;
    private String adventureId;
    private String price;

    public AdventureUtilityDto() {
    }

    public AdventureUtilityDto(String name, String adventureId, String price) {
        this.name = name;
        this.adventureId = adventureId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdventureId() {
        return adventureId;
    }

    public void setAdventureId(String adventureId) {
        this.adventureId = adventureId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
