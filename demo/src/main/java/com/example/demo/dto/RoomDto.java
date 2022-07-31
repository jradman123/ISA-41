package com.example.demo.dto;

import com.example.demo.model.cottages.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {

    private String number;
    private String cottageId;

    public RoomDto(Room room) {
        this.number=room.getNumberOfBeds().toString();
        this.cottageId=Long.toString(room.getCottage().getId());

    }
}
