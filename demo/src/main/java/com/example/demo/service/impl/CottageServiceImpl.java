package com.example.demo.service.impl;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.AverageMarkDto;
import com.example.demo.dto.ReservationViewDto;
import com.example.demo.dto.RoomDto;
import com.example.demo.model.Address;
import com.example.demo.model.Review;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.cottages.Room;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.*;

import com.example.demo.service.CottageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CottageReservationRepository cottageReservationRepository;




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
                Address address = new Address(newCottage.getStreetName(), newCottage.getStreetNumber(), newCottage.getCity(), newCottage.getCountry(),newCottage.getLongitude(),newCottage.getLatitude());
                Cottage cottage = new Cottage(newCottage.getName(),newCottage.getDescription(),Double.parseDouble(newCottage.getPrice()),address,owner,Integer.parseInt(newCottage.getNumberOfPeople()),newCottage.getCancelled_conditions(),null);
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
        if(reservations.isEmpty()) {

        List<Cottage> cottages=this.cottageRepository.findAll();
        for (Cottage cottage: cottages)
        {

           if(cottage.getId()==id) {
               cottage.setDeleted(true);
               cottageRepository.save(cottage);
           }

        }
            return new ResponseEntity<>(id, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.OK);
        }



    }

    @Override
    public Cottage findCottageById(Long id) {
        Cottage cottage= cottageRepository.findById(id).orElse(null);
         return cottage;

    }


    @Override
    @Transactional
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
                cottage.setNumberOfPerson(Integer.parseInt(cottageDto.getNumberOfPeople()));
                cottage.setCancelationConditions(cottageDto.getCancelled_conditions());

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

    public Double getAverageMarkByCottage(Long id) {
        List<CottageReservation> reservations=this.cottageReservationRepository.getAllForCottage(id);
        Double sum = 0.0;
        for (Review review : this.reviewRepository.findAll()) {
            for (CottageReservation reservation : reservations) {
                   if(reservation.getId()==review.getReservation().getId()) {
                       sum+=review.getMark();
                         }

            }

        }
        if(this.reviewRepository.findAll().size()==0) {
            return sum=0.0;
        }
        return sum/this.reviewRepository.findAll().size();
    }



    @Override
    public AverageMarkDto getCottageReport(Long id) {
       AverageMarkDto report=new AverageMarkDto();
       Double marks=getAverageMarkByCottage(id);
       report.setCottageMark(marks.toString());

       return report;
    }
}

