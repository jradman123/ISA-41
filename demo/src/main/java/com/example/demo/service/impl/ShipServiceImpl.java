package com.example.demo.service.impl;

import com.example.demo.dto.AverageMarkDto;
import com.example.demo.dto.CottageDto;
import com.example.demo.dto.ReservationViewDto;
import com.example.demo.dto.ShipDto;
import com.example.demo.model.Address;
import com.example.demo.model.Review;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.model.users.ShipOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.ShipOwnerRepository;
import com.example.demo.repository.ShipRepository;
import com.example.demo.repository.ShipReservationRepository;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ShipServiceImpl  implements ShipService {
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private UserServiceImpl userRepository;

    @Autowired
    private ShipReservationRepository shipReservationRepository;

    @Autowired
    private ShipOwnerRepository shipOwnerRepository;

    @Autowired
    private ReservationServiceImpl reservationService;

    @Autowired
    private ReviewRepository reviewRepository;






    @Override
    public ShipDto editShip(ShipDto shipDto) {
        for (Ship ship : shipRepository.findAll()) {
            if (shipDto.getId().equals(ship.getId().toString())) {
                ship.setName(shipDto.getName());
                ship.setDescription(shipDto.getDescription());
                ship.setPrice(Double.parseDouble(shipDto.getPrice()));
                ship.getAddress().setStreetNumber(shipDto.getStreetNumber());
                ship.getAddress().setStreetName(shipDto.getStreetName());
                ship.getAddress().setCity(shipDto.getCity());
                ship.getAddress().setCountry(shipDto.getCountry());
                ship.setCapacity(Integer.parseInt(shipDto.getCapacity()));
                ship.setLength(Double.parseDouble(shipDto.getLength()));
                ship.setNumberOfEngine(shipDto.getNumberOfEngine());
                ship.setStrengthOfEngine(Double.parseDouble(shipDto.getStrengthOfEngine()));
                ship.setMaxSpeed(Double.parseDouble(shipDto.getMaxSpeed()));
                ship.setType(shipDto.getType());
                ship.setCancelationConditions(Integer.parseInt(shipDto.getCancelationConditions()));
                ship.setFishingEquipment(shipDto.getFishingEquipment());




                shipRepository.save(ship);
                return new ShipDto(ship);

            }

        }
        return null;
    }

    @Override
    public Ship findShipById(Long objectId) {
        Ship ship= shipRepository.findById(objectId).orElse(null);
        return ship;
    }

    @Override
    public Ship create(Ship ship) {
        return shipRepository.save(ship);
    }

    @Override
    public AverageMarkDto getShipReport(Long id) {
        AverageMarkDto report=new AverageMarkDto();
        Double marks=getAverageMarkByShip(id);
        report.setCottageMark(marks.toString());
        return report;
    }

    public Double getAverageMarkByShip(Long id) {
        List<ShipReservation> reservations=this.shipReservationRepository.getAllForShip(id);
        Double sum = 0.0;
        for (Review review : this.reviewRepository.findAll()) {
            for (ShipReservation reservation : reservations) {
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
    public List<ShipDto> findAll() {
        List<ShipDto> ships = new ArrayList<>();

        for(Ship ship : shipRepository.findAll((Sort.by(Sort.Direction.ASC, "price")))){
            ships.add(new ShipDto(ship));}

        return ships;
    }



    @Override
    public Ship createShip(ShipDto newShip) {
        System.out.print("fjedsjfsjfdj"+newShip.getOwnerEmail());
        User user = this.userRepository.findByEmail(newShip.getOwnerEmail());
        System.out.print("a sad"+user.getEmail());
        for (ShipOwner owner : this.shipOwnerRepository.findAll()) {

            if (owner.getEmail().equals(user.getEmail())) {
                Address address = new Address(newShip.getStreetName(), newShip.getStreetNumber(), newShip.getCity(), newShip.getCountry(), newShip.getLongitude(), newShip.getLongitude());
                Ship ship = new Ship(newShip.getName(), newShip.getDescription(), Double.parseDouble(newShip.getPrice()),address, owner,Integer.parseInt(newShip.getCapacity()),Double.parseDouble(newShip.getMaxSpeed()),Integer.parseInt(newShip.getCancelationConditions()),Double.parseDouble(newShip.getLength()),Double.parseDouble(newShip.getStrengthOfEngine()),newShip.getFishingEquipment(),newShip.getNumberOfEngine(),newShip.getType(),null);
                return this.shipRepository.save(ship);
            }
        }
      return null;

    }

    @Override
    public ShipDto findShip(Long id) {
        Ship ship= shipRepository.findById(id).orElse(null);
        ShipDto shipDto=new ShipDto(ship);
        return shipDto;
    }

    @Override
    public List<ShipDto> getOwnerShips(String email) {
        List<ShipDto> ships = new ArrayList<>();
        for(Ship ship: shipRepository.findAll()){
            User user= userRepository.findByEmail(ship.getShipOwner().getEmail());
            if(user.getEmail().equals(email) & ship.isDeleted()==false){
                ShipDto shipDto=new ShipDto(ship);
                ships.add(shipDto);
            }
        }
        return  ships;
    }

    @Override
    public ResponseEntity<Long> deleteShip(Long id) {
        List<ReservationViewDto> reservations=reservationService.getReservationsByShip(id);
        if(reservations.isEmpty()) {

            List<Ship> ships = this.shipRepository.findAll();
            for (Ship ship : ships) {
                if (ship.getId() == id) {
                    ship.setDeleted(true);
                    shipRepository.save(ship);
                }

            }
            return new ResponseEntity<>(id, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(null,HttpStatus.OK);
        }


    }

}
