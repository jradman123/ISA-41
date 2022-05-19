package com.example.demo.service.impl;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.CreateCottageDto;
import com.example.demo.model.Address;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.CottageOwnerRepository;
import com.example.demo.repository.CottageRepository;

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
    private UserServiceImpl userRepository;
    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;



    @Override
    public List<CottageDto> findAll() {
        List<CottageDto> cottages = new ArrayList<>();

        for(Cottage cottage : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "price")))){
            cottages.add(new CottageDto(cottage));}

        return cottages;
    }

    @Override
    public Cottage createCottage(CreateCottageDto newCottage) {
        User user = this.userRepository.findByEmail(newCottage.getOwnerEmail());
        for (CottageOwner owner : this.cottageOwnerRepository.findAll()) {
            if (owner.getEmail().equals(user.getEmail())) {
                Address address = new Address(newCottage.getAddress().getStreetName(), newCottage.getAddress().getStreetNumber(), newCottage.getAddress().getCity(), newCottage.getAddress().getCountry());
                Cottage cottage = new Cottage(newCottage.getName(), newCottage.getDescription(), newCottage.getPrice(), address, owner);
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
    public CottageDto editCottage(CottageDto cottageDto) {
        for (Cottage cottage: cottageRepository.findAll()){
            if(cottageDto.getId().equals(cottage.getId())){
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
}

