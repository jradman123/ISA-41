package com.example.demo.dto;

public class ResponseFishingEquipment {

    private String id;
    private String fishingEquipmentName;

    public ResponseFishingEquipment() {
    }

    public ResponseFishingEquipment(String id, String fishingEquipmentName) {
        this.id = id;
        this.fishingEquipmentName = fishingEquipmentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFishingEquipmentName() {
        return fishingEquipmentName;
    }

    public void setFishingEquipmentName(String fishingEquipmentName) {
        this.fishingEquipmentName = fishingEquipmentName;
    }
}
