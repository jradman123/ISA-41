package com.example.demo.dto;

public class FishingEquipmentDto {

    private String fishingEquipmentName;
    private String adventureId;

    public FishingEquipmentDto() {
    }

    public FishingEquipmentDto(String fishingEquipmentName, String adventureId) {
        this.fishingEquipmentName = fishingEquipmentName;
        this.adventureId = adventureId;
    }

    public String getFishingEquipmentName() {
        return fishingEquipmentName;
    }

    public void setFishingEquipmentName(String fishingEquipmentName) {
        this.fishingEquipmentName = fishingEquipmentName;
    }

    public String getAdventureId() {
        return adventureId;
    }

    public void setAdventureId(String adventureId) {
        this.adventureId = adventureId;
    }
}
