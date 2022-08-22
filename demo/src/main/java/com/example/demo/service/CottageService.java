package com.example.demo.service;


import com.example.demo.dto.CottageDto;
import com.example.demo.dto.AverageMarkDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CottageService {
    public List<CottageDto> findAll();
    public CottageDto findCottage(Long id);
    public Cottage createCottage(CottageDto newCottage);
    public List<CottageDto> getOwnerCottages(String email);
    public ResponseEntity<Long> deleteCottage(Long id);

    public Cottage findCottageById(Long id);
    CottageDto editCottage(CottageDto cottageDto);


    public List<RoomDto> findRoomsByCottage(Long id);

    ResponseEntity<Long> deleteRoomByCottage(Long id, Long idCottage);

    Room createRoom(RoomDto newRoom);


    List<CottageDto> search(CottageDto dto);

    Cottage create(Cottage cottage);

    AverageMarkDto getCottageReport(Long id);
}
