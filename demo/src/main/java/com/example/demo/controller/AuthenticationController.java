package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.model.users.*;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @PostMapping("/login")
    public ResponseEntity<AuthenticatedUserDto> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {

        // Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
        // AuthenticationException
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
        // kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Kreiraj token za tog korisnika
        String role = userService.findByEmail(authenticationRequest.getEmail()).getUserType().toString();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUser().getEmail());
        int expiresIn = tokenUtils.getExpiredIn();
        AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto(authenticationRequest.getEmail(),role, new UserTokenState(jwt,expiresIn));
        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(authenticatedUserDto);
    }

    // Endpoint za registraciju novog korisnika
    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody RegistrationRequestDto userRequest, UriComponentsBuilder ucBuilder) {
        User existUser = this.userService.findByEmail(userRequest.getEmail());

        if (existUser != null) {
            throw new ResourceConflictException(userRequest.getEmail(), "Email already exists");
        }

        if(userRequest.getTypeOfRegistration().equals("INSTRUCTOR")) {
            Instructor instructor = this.userService.saveInstructor(userRequest);
            return new ResponseEntity<String>("Success!", HttpStatus.CREATED);
        }
        else if(userRequest.getTypeOfRegistration().equals("SHIP OWNER")) {
           ShipOwner shipOwner = this.userService.saveShipOwner(userRequest);
            return new ResponseEntity<String>("Success!", HttpStatus.CREATED);
        } else if(userRequest.getTypeOfRegistration().equals("COTTAGE OWNER")) {
            CottageOwner cottageOwner = this.userService.saveCottageOwner(userRequest);
            return new ResponseEntity<String>("Success!", HttpStatus.CREATED);
        } else if(userRequest.getTypeOfRegistration().equals("CLIENT")) {
            RegisteredUser registeredUser = this.userService.saveRegisteredUser(userRequest);
            return new ResponseEntity<String>("Success!", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Error!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
