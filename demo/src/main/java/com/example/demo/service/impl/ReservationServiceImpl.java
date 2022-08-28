package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageAvailability;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.cottages.CottageUtility;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipAvailability;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.model.ships.ShipUtility;
import com.example.demo.model.users.User;
import com.example.demo.repository.*;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
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
    private CottageAvailabilityRepository cottageAvailabilityRepository;

    @Autowired
    private CottageReservationRepository cottageReservationRepository;

    @Autowired
    private CottageUtilityRepository cottageUtilityRepository;

    @Autowired
    private ShipReservationRepository shipReservationRepository;

    @Autowired
    private ShipUtilityRepository shipUtilityRepository;


    @Autowired
    private ShipAvailabilityRepository shipAvailabilityRepository;


    @Autowired
    private EmailSenderServiceImpl emailSenderService;




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
    public Reservation createCottageReservation(CreateReservationDto createReservationDto) {

        User user=userService.findByEmail(createReservationDto.clientEmail);


        for(CottageReservation ct:cottageReservationRepository.getAllForCottage(Long.parseLong(createReservationDto.getObjectId()))) {

            if(createReservationDto.getResStart().equals(ct.getReservationStart()) || createReservationDto.getResEnd().equals(ct.getReservationEnd()) ||
                    createReservationDto.getResStart().equals(ct.getReservationEnd()) ||
                    createReservationDto.getResEnd().equals(ct.getReservationStart()) ||
                    (createReservationDto.getResEnd().isAfter(ct.getReservationStart()) && createReservationDto.getResEnd().isBefore(ct.getReservationEnd()))
                    || (createReservationDto.getResStart().isBefore(ct.getReservationStart()) && createReservationDto.getResEnd().isAfter(ct.getReservationEnd())) ||
                    (createReservationDto.getResStart().isAfter(ct.getReservationStart()) && createReservationDto.getResEnd().isBefore(ct.getReservationEnd()))
                    ||  (createReservationDto.getResStart().isAfter(ct.getReservationStart()) && createReservationDto.getResStart().isBefore(ct.getReservationEnd())&& createReservationDto.getResEnd().isAfter(ct.getReservationEnd())))
            {
                return null;
            }else {


                if (createReservationDto.getResStart().isAfter(LocalDateTime.now()) && createReservationDto.getResEnd().isAfter(createReservationDto.getResStart())) {

                    Reservation reservation = typeOfReservation(createReservationDto);

                    reservation.setRegisteredUser(user);
                    reservation.setHaveReport(false);
                    reservation.setReservationStart(createReservationDto.resStart);
                    reservation.setReservationEnd(createReservationDto.resEnd);
                    reservation.setIsReserved(false);
                    reservation.setPrice(createReservationDto.getPrice());
                    Set<CottageUtility> utilities = new HashSet<>();
                    for (ResponseUtility responseUtility : createReservationDto.getUtilities()) {
                        CottageUtility utility = cottageUtilityRepository.findById(Long.parseLong(responseUtility.getId())).get();
                        utilities.add(utility);


                    }
                    reservation.setCottageUtilities(utilities);


                    notifyUserForReservation(createReservationDto);
                    reservationRepository.save(reservation);

                    return reservation;

                } else {
                    throw new RuntimeException();
                }

               }


            }
        return null;
    }

    @Override
    public Reservation createShipReservation(CreateReservationDto createReservationDto) {

        User user=userService.findByEmail(createReservationDto.clientEmail);

        for(ShipReservation ct:shipReservationRepository.getAllForShip(Long.parseLong(createReservationDto.getObjectId()))) {


            if(createReservationDto.getResStart().equals(ct.getReservationStart()) || createReservationDto.getResEnd().equals(ct.getReservationEnd()) ||
                    createReservationDto.getResStart().equals(ct.getReservationEnd()) ||
                    createReservationDto.getResEnd().equals(ct.getReservationStart()) ||
                    (createReservationDto.getResEnd().isAfter(ct.getReservationStart()) && createReservationDto.getResEnd().isBefore(ct.getReservationEnd()))
                    || (createReservationDto.getResStart().isBefore(ct.getReservationStart()) && createReservationDto.getResEnd().isAfter(ct.getReservationEnd())) ||
                    (createReservationDto.getResStart().isAfter(ct.getReservationStart()) && createReservationDto.getResEnd().isBefore(ct.getReservationEnd()))
                    ||  (createReservationDto.getResStart().isAfter(ct.getReservationStart()) && createReservationDto.getResStart().isBefore(ct.getReservationEnd())&& createReservationDto.getResEnd().isAfter(ct.getReservationEnd())))
            {
                return null;
            }else{


                    if (createReservationDto.getResStart().isAfter(LocalDateTime.now()) && createReservationDto.getResEnd().isAfter(createReservationDto.getResStart())) {
                        Reservation reservation = typeOfReservation(createReservationDto);
                        reservation.setPrice(createReservationDto.getPrice());
                        reservation.setRegisteredUser(user);
                        reservation.setHaveReport(false);
                        reservation.setReservationStart(createReservationDto.resStart);
                        reservation.setReservationEnd(createReservationDto.resEnd);
                        reservation.setPrice(createReservationDto.getPrice());
                        reservation.setIsReserved(false);
                        Set<ShipUtility> utilities = new HashSet<>();
                        for (ResponseUtility responseUtility : createReservationDto.getUtilities()) {
                            ShipUtility utility=shipUtilityRepository.findById(Long.parseLong(responseUtility.getId())).get();
                            utilities.add(utility);


                        }
                        reservation.setShipUtilities(utilities);


                     notifyUserForReservation(createReservationDto);
                        reservationRepository.save(reservation);

                        return reservation;

                    } else {
                        throw new RuntimeException();
                    }



            }}
        return null;
    }


    @Override
    public void notifyUserForReservation(CreateReservationDto dto) {
        emailSenderService.sendEmail(dto.getClientEmail(),"Obavijest o rezervaciji","Rezervacija u dogovoru sa vlasnikom je kreirana.Rezervacija traje od "+dto.getResStart() + " do " + dto.getResEnd() +
                " po cijeni od " + dto.getPrice() + "€ za " + dto.getNumberOfPerson() + " osoba." +
                "Molimo da provjerite detalje na svom profilu!");
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

        }
        return null;
    }
}
