package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.exception.ResourceConflictException;
import com.example.demo.model.enumeration.UserType;
import com.example.demo.model.users.*;
import com.example.demo.security.TokenUtils;
import com.example.demo.service.impl.RegistrationForClientsServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RegistrationForClientsServiceImpl registrationForClientsService;

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));


            User userDb = userService.findByEmail(authenticationRequest.getEmail());
            if (userDb.isDeleted()) {
                return new ResponseEntity<>("Profile is deleted!", HttpStatus.BAD_REQUEST);
            }

            if (!userDb.getActivated()) {
                return new ResponseEntity<>("Profile is not activated!", HttpStatus.BAD_REQUEST);
            }
            // Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
            // kontekst
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Kreiraj token za tog korisnika
            UserType role = userService.findByEmail(authenticationRequest.getEmail()).getUserType();
            AuthenticatedUserDto authenticatedUserDto = new AuthenticatedUserDto();
            boolean firstLogin = false;
            boolean predefAdmin = false;
            if (role.equals(UserType.Admin)) {
                firstLogin = userService.isFirstLogin(authenticationRequest.getEmail());
                predefAdmin = userService.isPredefAdmin(authenticationRequest.getEmail());
            }
            UserDetails user = (UserDetails) authentication.getPrincipal();
            String jwt = tokenUtils.generateToken(user.getUser().getEmail());
            int expiresIn = tokenUtils.getExpiredIn();
            authenticatedUserDto.setRole(role.toString());
            authenticatedUserDto.setEmail(authenticationRequest.getEmail());
            authenticatedUserDto.setToken(new UserTokenState(jwt, expiresIn));
            authenticatedUserDto.setFirstLogin(firstLogin);
            authenticatedUserDto.setPredefAdmin(predefAdmin);
            return ResponseEntity.ok(authenticatedUserDto);
        }catch (AuthenticationException e){
            return new ResponseEntity<>("Bad credentials!", HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint za registraciju novog korisnika
    @CrossOrigin(origins = "*")
    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody RegistrationRequestDto userRequest, UriComponentsBuilder ucBuilder) throws UnknownHostException {

        User existUser = this.userService.findByEmail(userRequest.getEmail());

        if (existUser != null) {
            return new ResponseEntity<String>("Email already exists!", HttpStatus.BAD_REQUEST);
        }

        if(userRequest.getTypeOfRegistration().equals("INSTRUCTOR")) {
            this.userService.saveInstructor(userRequest);
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

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/verify-client/{email}")
    public ResponseEntity<String> confirmClient(@PathVariable String email) {
        User user = userService.findByEmail(email);
        registrationForClientsService.registerClient(user);
        return new ResponseEntity<>("Successfully registered!", HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping(value = "/addNewAdmin")
    public ResponseEntity<String> addNewAdmin(@RequestBody AdministratorRegistrationDto administratorRegistrationDto) {
        User existUser = this.userService.findByEmail(administratorRegistrationDto.getEmail());
        if (existUser != null) {
            return new ResponseEntity<String>("Email already exists!", HttpStatus.BAD_REQUEST);
        }
        userService.saveAdministrator(administratorRegistrationDto);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }



}
