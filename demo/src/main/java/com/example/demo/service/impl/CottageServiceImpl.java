package com.example.demo.service.impl;

import com.example.demo.dto.CottageDto;
import com.example.demo.dto.CreateCottageDto;
import com.example.demo.dto.ViewCottegeDto;
import com.example.demo.model.Address;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.users.CottageOwner;
import com.example.demo.model.users.User;
import com.example.demo.repository.CottageOwnerRepository;
import com.example.demo.repository.CottageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CottageServiceImpl {
    @Autowired
    private CottageRepository cottageRepository;
    @Autowired
    private UserServiceImpl userRepository;
    @Autowired
    private CottageOwnerRepository cottageOwnerRepository;



    public List<CottageDto> findAll() {
        List<CottageDto> cottages = new ArrayList<>();

        for(Cottage cottage : cottageRepository.findAll((Sort.by(Sort.Direction.ASC, "price")))){
            cottages.add(new CottageDto(cottage));}

        return cottages;
    }

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

    public CottageDto findCottage(Long id) {
        Cottage cottage= cottageRepository.findById(id).orElse(null);
        CottageDto cottageDto=new CottageDto(cottage);
        return cottageDto;
    }


    public List<Cottage> getCottagesFromOwner(String email) {
        List<Cottage> cottages = new ArrayList<>();
        for(Cottage cottage: cottageRepository.findAll()){
            User user= userRepository.findByEmail(cottage.getCottageOwner().getEmail());
            if(user.getEmail().equals(email)){
                cottages.add(new Cottage(cottage.getName(),cottage.getDescription(),cottage.getPrice(),cottage.getAddress(),cottage.getCottageOwner()));
            }
        }
        return  cottages;
    }
}

