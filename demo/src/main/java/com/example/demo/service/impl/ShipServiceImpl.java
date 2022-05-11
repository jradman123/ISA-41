package com.example.demo.service.impl;

import com.example.demo.dto.ShipDto;
import com.example.demo.model.ships.Ship;
import com.example.demo.repository.ShipRepository;
import com.example.demo.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ShipServiceImpl  implements ShipService {
    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private UserServiceImpl userRepository;



    @Override
    public List<ShipDto> findAll() {
        List<ShipDto> ships = new ArrayList<>();

        for(Ship ship : shipRepository.findAll((Sort.by(Sort.Direction.ASC, "price")))){
            ships.add(new ShipDto(ship));}

        return ships;
    }
   /*


    @Override
    public Ship createShip(CreateShipDto newShip) {
        User user = this.userRepository.findByEmail(newCottage.getOwnerEmail());
        for (ShipOwner owner : this.shipOwnerRepository.findAll()) {
            if (owner.getEmail().equals(user.getEmail())) {
                Address address = new Address(newShip.getAddress().getStreetName(), newShip.getAddress().getStreetNumber(), newShip.getAddress().getCity(), newCottage.getAddress().getCountry());
                Cottage cottage = new Cottage(newCottage.getName(), newCottage.getDescription(), newCottage.getPrice(), address, owner);
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
            User user= userRepository.findByEmail(cottage.getCottageOwner().getEmail());
            if(user.getEmail().equals(email)){
                ShipDto shipDto=new ShipDto(ship);
                ships.add(shipDto);
            }
        }
        return  ships;
    }

    @Override
    public ResponseEntity<Long> deleteShip(Long id) {

        List<Ship> ships=this.shipRepository.findAll();
        for (Ship ship: ships)
        {
           if(ship.getId()==id) {
               ship.setDeleted(true);
               shipRepository.save(ship);
           }

        }
        return new ResponseEntity<>(id, HttpStatus.OK);

    }
    */
}
