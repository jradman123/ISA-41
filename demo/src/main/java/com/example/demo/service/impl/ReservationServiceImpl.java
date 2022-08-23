package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.model.users.User;
import com.example.demo.repository.AdventureReservationRepository;
import com.example.demo.repository.CottageReservationRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ShipReservationRepository;
import com.example.demo.service.AdventureService;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService
{
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private CottageServiceImpl cottageService;

    @Autowired
    private ShipServiceImpl shipService;

    @Autowired
    private CottageReservationRepository cottageReservationRepository;

    @Autowired
    private ShipReservationRepository shipReservationRepository;

    @Autowired
    private EmailSenderServiceImpl emailSenderService;

    @Autowired
    private AdventureService adventureService;

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;


    @Override
    public List<ReservationViewDto> getReservationsByCottage(Long id) {
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(CottageReservation r : cottageReservationRepository.findAll()) {
            if(r.getCottage().getId()==id  && LocalDateTime.now().compareTo(r.getReservationEnd())<=0 ) {
                reservationDtos.add(new ReservationViewDto(r));
            }
        }

        return reservationDtos;
    }


    @Override
    public List<ReservationDto> getReservationsByBoat(Long id) {
        return null;
    }

    @Override
    public ReservationDto getById(Long id) {
        Reservation reservation= reservationRepository.findById(id).orElse(null);
        ReservationDto reservationDto=new ReservationDto(reservation);
        return reservationDto;
    }



    @Override
    public void createReservation(CreateReservationDto createReservationDto) {
        User user=userService.findByEmail(createReservationDto.clientEmail);
        Reservation reservation=typeOfReservation(createReservationDto);

       System.out.print("OPPPPPPPPEPEEEEEEEETJOOOOOJ"+createReservationDto.getPrice());
        reservation.setPrice(createReservationDto.getPrice());
        reservation.setRegisteredUser(user);
        reservation.setHaveReport(false);


      if(createReservationDto.getResStart().isAfter(LocalDateTime.now()) && createReservationDto.getResEnd().isAfter(createReservationDto.getResStart())) {


        reservation.setReservationStart(createReservationDto.resStart);
            reservation.setReservationEnd(createReservationDto.resEnd);
            reservation.setIsReserved(false);
            notifyUserForReservation(createReservationDto);

       }else { throw new RuntimeException();}

        reservationRepository.save(reservation);




    }

    @Override
    public void notifyUserForReservation(CreateReservationDto dto) {
        emailSenderService.sendEmail(dto.getClientEmail(),"Obavijest o rezervaciji","Rezervacija u dogovoru sa vlasnikom je kreirana.Rezervacija traje od "+dto.getResStart() + " do " + dto.getResEnd() +
                " po cijeni od " + dto.getPrice() + "€ za " + dto.getNumberOfPerson() + " osoba." +
                "Molimo da potvrdite i provjerite detalje na svom profilu.Klikom na link potvrdjujete rezervaciju!");
    }

    @Override
    public List<ReservationViewDto> getPastReservationsByCottage(Long id) {
        System.out.print("uslaaaaa");
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(CottageReservation r : cottageReservationRepository.findAll()) {
            if(r.getCottage().getId()==id && LocalDateTime.now().compareTo(r.getReservationEnd())>0) {
                reservationDtos.add(new ReservationViewDto(r));

            }
        }

        return reservationDtos;
    }

    @Override
    public List<ReservationViewDto> getReservationsByShip(Long id) {
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(ShipReservation r : shipReservationRepository.findAll()) {
            if(r.getShip().getId()==id  && LocalDateTime.now().compareTo(r.getReservationEnd())<=0 ) {
                reservationDtos.add(new ReservationViewDto(r));
            }
        }

        return reservationDtos;
    }

    @Override
    public List<ReservationViewDto> getPastReservationsByShip(Long id) {
        System.out.print("uslaaaaa");
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(ShipReservation r : shipReservationRepository.findAll()) {
            if(r.getShip().getId()==id && LocalDateTime.now().compareTo(r.getReservationEnd())>0) {
                reservationDtos.add(new ReservationViewDto(r));

            }
        }

        return reservationDtos;
    }

    @Override
    public List<ReservationViewDto> getPastReservationsForAdventure(int id) {
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(AdventureReservation r : adventureReservationRepository.findAll()) {
            if(r.getAdventure().getId()==id && LocalDateTime.now().isAfter(r.getReservationEnd())) {
                reservationDtos.add(new ReservationViewDto(r));

            }
        }
        return reservationDtos;
    }

    @Override
    public List<ReservationViewDto> getReservationsForAdventure(int id) {
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(AdventureReservation r : adventureReservationRepository.findAll()) {
            if(r.getAdventure().getId()==id  && (LocalDateTime.now().isBefore(r.getReservationEnd()) && LocalDateTime.now().isAfter(r.getReservationStart()))) {
                reservationDtos.add(new ReservationViewDto(r));
            }
        }

        return reservationDtos;
    }

    private Reservation typeOfReservation(CreateReservationDto createReservationDto) {
        if(createReservationDto.typeOfRes.equals("COTTAGE")) {
            CottageReservation cottageReservation=new CottageReservation();
            Cottage cottage=cottageService.findCottageById(Long.parseLong(createReservationDto.getObjectId()));
            cottageReservation.setCottage(cottage);
            cottageReservation.setNumberOfPerson(createReservationDto.getNumberOfPerson());
            Set<CottageReservation> cottageReservations=cottage.getCottageReservations();
            cottageReservations.add(cottageReservation);
            cottage.setCottageReservations(cottageReservations);
            return cottageReservation;


        }else if(createReservationDto.typeOfRes.equals("BOAT")) {
            ShipReservation shipReservation=new ShipReservation();
            Ship ship=shipService.findShipById(Long.parseLong(createReservationDto.getObjectId()));
            shipReservation.setShip(ship);
            shipReservation.setNumberOfPerson(ship.getCapacity());
            Set<ShipReservation> shipReservations=ship.getShipReservations();
            shipReservations.add(shipReservation);
            ship.setShipReservations(shipReservations);
            return shipReservation;


        }else if(createReservationDto.typeOfRes.equals("ADVENTURE")) {
            AdventureReservation adventureReservation=new AdventureReservation();
            Adventure adventure=adventureService.findAdventure(Integer.parseInt(createReservationDto.getObjectId()));
            adventureReservation.setAdventure(adventure);
            adventureReservation.setNumberOfPerson(createReservationDto.getNumberOfPerson());
            Set<AdventureReservation> adventureReservations=adventure.getAdventureReservations();
            adventureReservations.add(adventureReservation);
            adventure.setAdventureReservations(adventureReservations);
            return adventureReservation;
        }
        return null;
    }
}
