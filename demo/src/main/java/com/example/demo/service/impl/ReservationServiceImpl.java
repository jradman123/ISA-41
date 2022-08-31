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
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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


    @Autowired
    private CottageQuickReservationServiceImpl cottageQuickReservationService;


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
        boolean reservations=checkDates(createReservationDto.getResStart(),createReservationDto.getResEnd(),createReservationDto.getObjectId());
        boolean availability=checkAvailability(createReservationDto.getResStart(),createReservationDto.getResEnd(),createReservationDto.getObjectId());
        boolean app=this.cottageQuickReservationService.checkApp(createReservationDto.getResStart(),createReservationDto.getResEnd(),createReservationDto.getObjectId());


        if(reservations && availability && app) {


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
               return null;
            }


        }

return null;
    }

    public boolean checkAvailability(LocalDateTime startDate,LocalDateTime endDate,String objectId) {
        for(CottageAvailability ca:this.cottageAvailabilityRepository.getAllForCottage(Long.parseLong(objectId))) {
            if(startDate.isAfter(ca.getStartDate()) && endDate.isBefore(ca.getEndDate())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkDates(LocalDateTime startDate,LocalDateTime endDate,String objectId) {
        for (CottageReservation ct : cottageReservationRepository.getAllForCottage(Long.parseLong(objectId))) {

            if (startDate.equals(ct.getReservationStart()) ||
                    startDate.equals(ct.getReservationEnd()) ||

                    endDate.equals(ct.getReservationEnd()) ||
                    endDate.equals(ct.getReservationStart())  ||
                    (startDate.isAfter(ct.getReservationStart()) && startDate.isBefore(ct.getReservationEnd()) && endDate.isAfter(ct.getReservationEnd()))
                    || (endDate.isAfter(ct.getReservationStart()) && endDate.isBefore(ct.getReservationEnd()))
                    || (startDate.isBefore(ct.getReservationStart()) && endDate.isAfter(ct.getReservationEnd())) ||
                    (startDate.isAfter(ct.getReservationStart()) && endDate.isBefore(ct.getReservationEnd())))
                    {
             return false;

            }
        }
        return true;
    }

    @Override
    public Reservation createShipReservation(CreateReservationDto createReservationDto) {

        User user=userService.findByEmail(createReservationDto.clientEmail);


          boolean reservations=checkDates(createReservationDto.getResStart(),createReservationDto.getResEnd(),createReservationDto.getObjectId());
        boolean availability=checkAvailability(createReservationDto.getResStart(),createReservationDto.getResEnd(),createReservationDto.getObjectId());


        if(reservations && availability) {

              if (createReservationDto.getResStart().isAfter(LocalDateTime.now()) && createReservationDto.getResEnd().isAfter(createReservationDto.getResStart())) {
                  Reservation reservation = typeOfReservation(createReservationDto);
                  reservation.setPrice(createReservationDto.getPrice());
                  reservation.setRegisteredUser(user);
                  reservation.setHaveReport(false);
                  reservation.setReservationStart(createReservationDto.resStart);
                  reservation.setReservationEnd(createReservationDto.resEnd);
                  reservation.setPrice(createReservationDto.getPrice());
                  reservation.setIsReserved(false);
                  reservation.setId(Long.parseLong(createReservationDto.getObjectId()));
                  Set<ShipUtility> utilities = new HashSet<>();
                  for (ResponseUtility responseUtility : createReservationDto.getUtilities()) {
                      ShipUtility utility = shipUtilityRepository.findById(Long.parseLong(responseUtility.getId())).get();
                      utilities.add(utility);


                  }
                  reservation.setShipUtilities(utilities);


                  notifyUserForReservation(createReservationDto);
                  reservationRepository.save(reservation);

                  return reservation;

              } else {
                 return null;
              }


          }

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
