package com.example.demo.service.impl;

import com.example.demo.dto.*;
import com.example.demo.mapper.ReservationMapper;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.adventures.AdventureUtility;
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

import com.example.demo.service.AdventureQuickReservationService;
import com.example.demo.service.AdventureService;
import com.example.demo.service.InstructorAvailabilityService;


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
    private AdventureService adventureService;

    @Autowired
    private AdventureReservationRepository adventureReservationRepository;

    @Autowired
    private CottageQuickReservationServiceImpl cottageQuickReservationService;


    @Autowired
    private AdventureUtilityRepository adventureUtilityRepository;

    @Autowired
    private InstructorAvailabilityService instructorAvailabilityService;

    @Autowired
    private AdventureQuickReservationService adventureQuickReservationService;

    @Autowired
    private ReservationMapper reservationMapper;

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
        reservation.setType(createReservationDto.getTypeOfRes());




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

    @Override
    public List<ReservationViewDto> getFutureReservationsForAdventure(int id) {
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(AdventureReservation r : adventureReservationRepository.findAll()) {
            if(r.getAdventure().getId()==id  && (LocalDateTime.now().isBefore(r.getReservationEnd()) && LocalDateTime.now().isBefore(r.getReservationStart()))) {
                reservationDtos.add(new ReservationViewDto(r));
            }
        }

        return reservationDtos;
    }

    @Override
    public boolean reservationsExistForAdventure(int id) {
        List<AdventureReservation> reservations = new ArrayList<>();
        for(AdventureReservation r : adventureReservationRepository.findAll()){
            if(r.getAdventure().getId() == id &&
                    ((LocalDateTime.now().isBefore(r.getReservationEnd()) && LocalDateTime.now().isBefore(r.getReservationStart())) ||
                    (LocalDateTime.now().isBefore(r.getReservationEnd()) && LocalDateTime.now().isAfter(r.getReservationStart())))) {
                reservations.add(r);
            }
        }
        if (reservations.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String createAdventureReservation(CreateReservationDto createReservationDto) {
        int instructorId = adventureService.findAdventure(Integer.parseInt(createReservationDto.getObjectId())).getInstructor().getId();
        int clientId = userService.findByEmail(createReservationDto.getClientEmail()).getId();
        boolean available = instructorAvailabilityService.isInstructorAvailable(instructorId,createReservationDto.getResStart(),createReservationDto.getResEnd());
        boolean hasReservations = hasInstructorReservationsForRange(instructorId,createReservationDto.getResStart(),createReservationDto.getResEnd());
        boolean hasClientReservations = hasClientReservations(clientId,createReservationDto.getResStart(),createReservationDto.getResEnd());
        boolean hasQuickReservations = adventureQuickReservationService.hasQuickReservation(instructorId,createReservationDto.getResStart(),createReservationDto.getResEnd());
        if(available && !hasReservations && !hasClientReservations && !hasQuickReservations){
            createReservation(createReservationDto);
            return "Success!";
        }else{
            return "Date range is not free!";
        }
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
            adventureReservation.setInstructorId(adventure.getInstructor().getId());
            Set<AdventureUtility> utilities = new HashSet<>();
            if(createReservationDto.getUtilities() != null) {
                for (ResponseUtility dto : createReservationDto.getUtilities()) {
                    utilities.add(adventureUtilityRepository.findById(Long.parseLong(dto.getId())).get());
                }
            }
            adventureReservation.setUtilities(utilities);
            adventureReservations.add(adventureReservation);
            adventure.setAdventureReservations(adventureReservations);
            return adventureReservation;
        }
        return null;
    }

    @Override
    public boolean hasInstructorReservationsForRange(int id, LocalDateTime startTime, LocalDateTime endTime) {
        for (AdventureReservation reservation : getAllInstructorsAdventures(id)) {
            if((startTime.isAfter(reservation.getReservationStart()) && endTime.isBefore(reservation.getReservationEnd())) ||
                    (startTime.isBefore(reservation.getReservationStart()) && endTime.isAfter(reservation.getReservationEnd())) ||
                    (startTime.isBefore(reservation.getReservationStart()) && endTime.isBefore(reservation.getReservationEnd()) && endTime.isAfter(reservation.getReservationStart())) ||
                    (startTime.isBefore(reservation.getReservationEnd()) && endTime.isAfter(reservation.getReservationEnd()) && startTime.isAfter(reservation.getReservationStart()))){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<ReservationViewDto> getAllCurrentAndFutureInstructorsReservations(int instructorId) {
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(AdventureReservation r : adventureReservationRepository.getAllCurrentAndFutureInstructorsReservations(instructorId,LocalDateTime.now())) {
                reservationDtos.add(new ReservationViewDto(r));
        }

        return reservationDtos;
    }

    @Override
    public List<ReservationViewDto> getAllInstructorsReservations(int instructorId) {
        List<ReservationViewDto> reservationDtos=new ArrayList<>();
        for(AdventureReservation r : adventureReservationRepository.getAllInstructorsReservations(instructorId)) {
            reservationDtos.add(new ReservationViewDto(r));
        }

        return reservationDtos;
    }

    @Override
    public DetailsAboutReservation getDetails(Long id) {
        String type = reservationRepository.findById(id).get().getType();
        if(type.equals("ADVENTURE")){
            return reservationMapper.fromAdventureReservation(adventureReservationRepository.findById(id).get());
        }else if(type.equals("SHIP")){
            return reservationMapper.fromShipReservation(shipReservationRepository.findById(Integer.parseInt(id.toString())).get());
        }else if(type.equals("COTTAGE")){
            return reservationMapper.fromCottageReservation(cottageReservationRepository.findById(id).get());
        }
        return null;

    }

    private List<AdventureReservation> getAllInstructorsAdventures(int id){
        return adventureReservationRepository.getAllCurrentAndFutureInstructorsReservations(id,LocalDateTime.now());
    }


    private boolean hasClientReservations(int clientId,LocalDateTime startTime,LocalDateTime endTime) {
        List<AdventureReservation> reservations = adventureReservationRepository.getAllReservationsForClient(clientId,LocalDateTime.now());
        for (AdventureReservation reservation : reservations) {
            if ((startTime.isAfter(reservation.getReservationStart()) && endTime.isBefore(reservation.getReservationEnd())) ||
                    (startTime.isBefore(reservation.getReservationStart()) && endTime.isAfter(reservation.getReservationEnd())) ||
                    (startTime.isBefore(reservation.getReservationStart()) && endTime.isBefore(reservation.getReservationEnd()) && endTime.isAfter(reservation.getReservationStart())) ||
                    (startTime.isBefore(reservation.getReservationEnd()) && endTime.isAfter(reservation.getReservationEnd()) && startTime.isAfter(reservation.getReservationStart()))) {
                return true;
            }
        }
        return false;
    }


}
