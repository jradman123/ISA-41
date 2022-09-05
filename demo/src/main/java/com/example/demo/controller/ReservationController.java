package com.example.demo.controller;


import com.example.demo.dto.*;

import com.example.demo.model.adventures.AdventureReservation;


import com.example.demo.model.reservation.Reservation;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.ReservationService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {


    @Autowired
    ReservationService reservationService;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('Admin') ")
    @GetMapping(value = "/findReservationsByCottage/{id}")
    public List<ReservationViewDto> getReservationsByCottage(@PathVariable Long id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getReservationsByCottage(id);
        return reservationDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('Admin') ")
    @GetMapping(value = "/findPastReservationsByCottage/{id}")
    public List<ReservationViewDto> getPastReservationsByCottage(@PathVariable Long id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getPastReservationsByCottage(id);
        for(ReservationViewDto reservationViewDto:reservationDtos)
            System.out.print(reservationViewDto.getResEnd());
        return reservationDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser') || hasAuthority('Admin') ")
    @GetMapping(value = "/findReservationsByShip/{id}")
    public List<ReservationViewDto> getReservationsByShip(@PathVariable Long id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getReservationsByShip(id);
        return reservationDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser') || hasAuthority('Admin') ")
    @GetMapping(value = "/findPastReservationsByShip/{id}")
    public List<ReservationViewDto> getPastReservationsByShip(@PathVariable Long id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getPastReservationsByShip(id);
        for(ReservationViewDto reservationViewDto:reservationDtos)
            System.out.print(reservationViewDto.getResEnd());
        return reservationDtos;
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('ShipAdvertiser') || hasAuthority('Admin') ")
    @GetMapping(value = "/findReservationsByBoat/{id}")
    public List<ReservationDto> getReservationsByBoat(@PathVariable Long id) {
        List<ReservationDto> reservationDtos = this.reservationService.getReservationsByBoat(id);
        return reservationDtos;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "getById/{id}")
    public ReservationDto getById(@PathVariable Long id) {
        return this.reservationService.getById(id);
    }

    @PreAuthorize("hasAuthority('CottageAdvertiser') || hasAuthority('ShipAdvertiser') || hasAuthority('Instructor')")
    @PostMapping(value="/createReservation")
    public ResponseEntity<String> createReservation(@RequestBody CreateReservationDto createReservationDto) {
        String result = reservationService.createAdventureReservation(createReservationDto);
        if(result.equals("Success!")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/find-past-reservations-by-adventure/{id}")
    public ResponseEntity<List<ReservationViewDto>> getPastReservationsByAdventure(@PathVariable int id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getPastReservationsForAdventure(id);
        return new ResponseEntity<>(reservationDtos,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/find-current-reservations-by-adventure/{id}")
    public ResponseEntity<List<ReservationViewDto>> getReservationsByAdventure(@PathVariable int id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getReservationsForAdventure(id);
        return new ResponseEntity<>(reservationDtos,HttpStatus.OK);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/find-future-reservations-by-adventure/{id}")
    public ResponseEntity<List<ReservationViewDto>> getFutureReservationsByAdventure(@PathVariable int id) {
        List<ReservationViewDto> reservationDtos = this.reservationService.getFutureReservationsForAdventure(id);
        return new ResponseEntity<>(reservationDtos,HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Instructor') || hasAuthority('Admin')")
    @GetMapping(value = "/reservations-exist-for-adventure/{id}")
    public ResponseEntity<String> reservationsExistForAdventure(@PathVariable int id) {
        if(reservationService.reservationsExistForAdventure(id)){
            return new ResponseEntity<>("\"TRUE\"",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("\"FALSE\"",HttpStatus.OK);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Instructor')")
    @GetMapping(value = "/all-instructors")
    public List<ReservationViewDto> getReservationsForInstructor(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String email = tokenUtils.getEmailFromToken(token);
        List<ReservationViewDto> reservationViewDtos=reservationService.getAllInstructorsReservations(userService.findByEmail(email).getId());
        return reservationViewDtos;
    }



    @PreAuthorize("hasAuthority('CottageAdvertiser')")
    @PostMapping(value="/createCottageReservation")
    public ResponseEntity<String> createCottageReservation(@RequestBody CreateReservationDto createReservationDto) {
        Reservation reservation= reservationService.createCottageReservation(createReservationDto);
        if(reservation==null) {
            return new ResponseEntity<String>("NO!", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("SUCCESS!", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ShipAdvertiser')")
    @PostMapping(value="/createShipReservation")
    public ResponseEntity<String> createShipReservation(@RequestBody CreateReservationDto createReservationDto) {
        Reservation reservation= reservationService.createShipReservation(createReservationDto);
        if(reservation==null) {
            return new ResponseEntity<String>("NO!", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("SUCCESS!", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping(value = "/{id}/details")
    public ResponseEntity<DetailsAboutReservation> getDetails(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.getDetails(id),HttpStatus.OK);
    }

}
