package com.example.demo.dto;

import com.example.demo.model.cottages.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    private String id;
    private Integer number;
    private String cottageId;

    public RoomDto(Room room) {
        this.id=room.getId().toString();
        this.number=room.getNumberOfBeds();
        this.cottageId=Long.toString(room.getCottage().getId());

    }
}
