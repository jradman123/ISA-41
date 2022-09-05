package com.example.demo.service.impl;

import com.example.demo.dto.AdventureAverageRating;
import com.example.demo.model.Review;
import com.example.demo.model.adventures.*;
import com.example.demo.repository.AdventureRepository;
import com.example.demo.repository.AdventureReservationRepository;
import com.example.demo.service.AdventureService;
import com.example.demo.service.ReservationService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdventureServiceImpl implements AdventureService {

    @Autowired
    private AdventureRepository adventureRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;

    @Override
    public List<Adventure> getAllForInstructor(int instructorId) {
        return adventureRepository.getAllForInstructor(instructorId);
    }

    @Override
    public Adventure createAdventure(Adventure newAdventure) {
        return adventureRepository.save(newAdventure);
    }

    @Override
    public Adventure findAdventure(int id) {
        return adventureRepository.findById(id).get();
    }

    @Override
    public Adventure updateAdventure(Adventure updatedAdventure,int id) {
        Adventure adventure = adventureRepository.findById(id).get();
        if(!reservationService.reservationsExistForAdventure(id)) {
            adventure.setName(updatedAdventure.getName());
            adventure.setDescription(updatedAdventure.getDescription());
            adventure.setPrice(updatedAdventure.getPrice());
            adventure.getAddress().setStreetNumber(updatedAdventure.getAddress().getStreetNumber());
            adventure.getAddress().setStreetName(updatedAdventure.getAddress().getStreetName());
            adventure.getAddress().setCity(updatedAdventure.getAddress().getCity());
            adventure.getAddress().setCountry(updatedAdventure.getAddress().getCountry());
            adventure.getAddress().setLongitude(updatedAdventure.getAddress().getLongitude());
            adventure.getAddress().setLatitude(updatedAdventure.getAddress().getLatitude());
            adventure.setGuestLimit(updatedAdventure.getGuestLimit());
            adventure.setCancellationConditions(updatedAdventure.getCancellationConditions());
        }
        Adventure saved = adventureRepository.save(adventure);
        return saved;
    }

    @Override
    public Adventure addRule(int id, AdventureRule rule) {
        Adventure adventure = findAdventure(id);
        Set<AdventureRule> adventureRules = new HashSet<>();
        for (AdventureRule rules: adventure.getRules()) {
            adventureRules.add(rules);
        }
        adventureRules.add(rule);
        adventure.setRules(adventureRules);
        adventureRepository.save(adventure);
        return adventure;
    }

    @Override
    public Set<AdventureRule> getRulesByAdventure(Adventure adventure) {
        Adventure adventureDb = findAdventure(adventure.getId());
        Set<AdventureRule> adventureRules = new HashSet<>();
        for (AdventureRule rules: adventureDb.getRules()) {
            if(!rules.isDeleted()) {
                adventureRules.add(rules);
            }
        }
        return adventureRules;
    }

    @Override
    public Adventure addFishingEquipment(int id, FishingEquipment fishingEquipment) {
        Adventure adventure = findAdventure(id);
        Set<FishingEquipment> adventureFishingEquipments = new HashSet<>();
        for (FishingEquipment equipment: adventure.getFishingEquipments()) {
            adventureFishingEquipments.add(equipment);
        }
        adventureFishingEquipments.add(fishingEquipment);
        adventure.setFishingEquipments(adventureFishingEquipments);
        adventureRepository.save(adventure);
        return adventure;
    }

    @Override
    public Set<FishingEquipment> getFishingEquipmentByAdventure(Adventure adventure) {
        Adventure adventureDb = findAdventure(adventure.getId());
        Set<FishingEquipment> adventureFishingEquipments = new HashSet<>();
        for (FishingEquipment equipment: adventureDb.getFishingEquipments()) {
            if(!equipment.isDeleted()) {
                adventureFishingEquipments.add(equipment);
            }
        }
        return adventureFishingEquipments;
    }

    @Override
    public Adventure addUtility(int id, AdventureUtility utility) {
        Adventure adventure = findAdventure(id);
        Set<AdventureUtility> adventureUtilities = new HashSet<>();
        for (AdventureUtility adventureUtility: adventure.getUtilities()) {
            adventureUtilities.add(adventureUtility);
        }
        adventureUtilities.add(utility);
        adventure.setUtilities(adventureUtilities);
        adventureRepository.save(adventure);
        return adventure;
    }

    @Override
    public Set<AdventureUtility> getUtilitiesByAdventure(Adventure adventure) {
        Adventure adventureDb = findAdventure(adventure.getId());
        Set<AdventureUtility> adventureUtilities = new HashSet<>();
        for (AdventureUtility utility: adventureDb.getUtilities()) {
            if(!utility.isDeleted()) {
                adventureUtilities.add(utility);
            }
        }
        return adventureUtilities;
    }

    @Override
    public Adventure deleteAdventure(int id) {
        Adventure adventure = adventureRepository.findById(id).get();
        if(!reservationService.reservationsExistForAdventure(id)) {
            adventure.setDeleted(true);
        }
        return adventureRepository.save(adventure);
    }

    @Override
    public AdventureAverageRating getRatingForAdventure(int id) {
        AdventureAverageRating averageRating=new AdventureAverageRating();
        Double rating=calculateAverageRating(id);
        averageRating.setAverageRating(rating.toString());
        return averageRating;
    }

    @Override
    public List<Adventure> getAllUndeleted() {
        return adventureRepository.getAllUndeleted();
    }

    private Double calculateAverageRating(int id) {
        List<AdventureReservation> reservations=adventureReservationRepository.getAllForAdventure(id);
        Double sum = 0.0;
        int numberOfRatings = 0;
        for (AdventureReservation reservation : reservations) {
            for (Review review : this.reviewService.findAllApprovedForReservation(reservation.getId())) {
                numberOfRatings++;
                sum+=review.getMark();
            }
        }
        if(numberOfRatings>0) {
            return sum / numberOfRatings;
        }else{
            return 0.0;
        }
    }


}
