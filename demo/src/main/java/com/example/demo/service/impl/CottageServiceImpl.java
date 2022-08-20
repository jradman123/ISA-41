package com.example.demo.service.impl;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.ReservationViewDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.model.Address;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.Room;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.CottageOwnerRepository;
import com.example.demo.repository.CottageRepository;

import com.example.demo.repository.RoomRepository;
import com.example.demo.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CottageServiceImpl implements CottageService {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserServiceImpl userRepository;
    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;

    @Autowired
    private ReservationServiceImpl reservationService;


    @Override
    public List<CottageDto> findAll() {
        List<CottageDto> cottages = new ArrayList<>();

        for(Cottage cottage : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "price")))){
            cottages.add(new CottageDto(cottage));}

        return cottages;
    }

    @Override
    public Cottage createCottage(CottageDto newCottage) {
        User user = this.userRepository.findByEmail(newCottage.getOwnerEmail());
        for (CottageOwner owner : this.cottageOwnerRepository.findAll()) {
            if (owner.getEmail().equals(user.getEmail())) {
                Address address = new Address(newCottage.getStreetName(), newCottage.getStreetNumber(), newCottage.getCity(), newCottage.getCountry());
                Cottage cottage = new Cottage(newCottage.getName(),newCottage.getDescription(),Double.parseDouble(newCottage.getPrice()),address,owner,Integer.parseInt(newCottage.getNumberOfPeople()));
                return this.cottageRepository.save(cottage);
            }
        }
        return null;

    }

    @Override
    public CottageDto findCottage(Long id) {
        Cottage cottage= cottageRepository.findById(id).orElse(null);
        CottageDto cottageDto=new CottageDto(cottage);
        return cottageDto;
    }

    @Override
    public List<CottageDto> getOwnerCottages(String email) {
        List<CottageDto> cottages = new ArrayList<>();
        for(Cottage cottage: cottageRepository.findAll()){
            User user= userRepository.findByEmail(cottage.getCottageOwner().getEmail());
            if(user.getEmail().equals(email) & cottage.isDeleted()==false){
                CottageDto cottageDto=new CottageDto(cottage);
                cottages.add(cottageDto);
            }
        }
        return  cottages;
    }

    @Override
    public ResponseEntity<Long> deleteCottage(Long id) {
        List<ReservationViewDto> reservations=reservationService.getReservationsByCottage(id);
        if(!reservations.isEmpty()) {
            return new ResponseEntity<>(id,HttpStatus.OK);
        }

        List<Cottage> cottages=this.cottageRepository.findAll();
        for (Cottage cottage: cottages)
        {

           if(cottage.getId()==id) {
               cottage.setDeleted(true);
               cottageRepository.save(cottage);
           }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }

    @Override
    public Cottage findCottageById(Long id) {
        Cottage cottage= cottageRepository.findById(id).orElse(null);
         return cottage;

    }


    @Override
    public CottageDto editCottage(CottageDto cottageDto) {
        for (Cottage cottage : cottageRepository.findAll()) {
            if (cottageDto.getId().equals(cottage.getId().toString())) {
                cottage.setName(cottageDto.getName());
                cottage.setDescription(cottageDto.getDescription());
                cottage.setPrice(Double.parseDouble(cottageDto.getPrice()));
                cottage.getAddress().setStreetNumber(cottageDto.getStreetNumber());
                cottage.getAddress().setStreetName(cottageDto.getStreetName());
                cottage.getAddress().setCity(cottageDto.getCity());
                cottage.getAddress().setCountry(cottageDto.getCountry());

                cottageRepository.save(cottage);
                return new CottageDto(cottage);

            }

        }
        return null;
    }

    @Override
    public List<RoomDto> findRoomsByCottage(Long id) {
        List<RoomDto> roomdtos = new ArrayList<>();
        for (Room room : roomRepository.findAll()) {
            if (room.getCottage() != null) {
                if (id.equals(room.getCottage().getId()) & room.isDeleted()==false) {
                    roomdtos.add(new RoomDto(room));
                }
            }
        }
        return roomdtos;
    }

    @Override
    public ResponseEntity<Long> deleteRoomByCottage(Long id, Long idCottage) {
        List<Room> rooms=this.roomRepository.findAll();
        for (Room room: rooms)
        {
            if(room.getCottage().getId()==idCottage & room.getId()==id) {
                room.setDeleted(true);
                roomRepository.save(room);
            }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }



    @Override
    public Room createRoom(RoomDto newRoom) {

        Cottage cottage=this.findCottageById(Long.parseLong(newRoom.getCottageId()));

        Room room = new Room();
                room.setCottage(cottage);
                room.setNumberOfBeds(newRoom.getNumber());
                room.setDeleted(false);

                this.roomRepository.save(room);
                return room;

        }




    @Override
    public List<CottageDto> search(CottageDto dto) {
        List<CottageDto> ret = new ArrayList<>();

        return ret;
    }

    @Override
    public Cottage create(Cottage cottage) {
        return cottageRepository.save(cottage);
    }
}

