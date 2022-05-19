package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.model.Address;
import com.example.demo.model.enumeration.UserType;
import com.example.demo.model.users.*;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public Instructor saveInstructor(RegistrationRequestDto userRequest) {
		Instructor instructor = new Instructor();
		instructor.setFirstName(userRequest.getFirstName());
		instructor.setLastName(userRequest.getLastName());
		instructor.setAddress(new Address(userRequest.getStreetName(),userRequest.getStreetNumber(),userRequest.getCity(),userRequest.getCountry()));
		instructor.setEmail(userRequest.getEmail());
		instructor.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		instructor.setDescriptionOfRegistration(userRequest.getDescriptionOfRegistration());
		instructor.setActivated(true);
		instructor.setDeleted(false);
		instructor.setPhoneNumber(userRequest.getPhoneNumber());
		instructor.setUserType(UserType.Instructor);
		Instructor saved = userRepository.save(instructor);
		RegistrationRequest request = registrationRequestRepository.save(new RegistrationRequest(userRequest.getEmail()));
		return saved;
	}

	@Override
	public ShipOwner saveShipOwner(RegistrationRequestDto userRequest) {
		ShipOwner shipOwner = new ShipOwner();
		shipOwner.setFirstName(userRequest.getFirstName());
		shipOwner.setLastName(userRequest.getLastName());
		shipOwner.setAddress(new Address(userRequest.getStreetName(),userRequest.getStreetNumber(),userRequest.getCity(),userRequest.getCountry()));
		shipOwner.setEmail(userRequest.getEmail());
		shipOwner.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		shipOwner.setDescriptionOfRegistration(userRequest.getDescriptionOfRegistration());
		shipOwner.setActivated(false);
		shipOwner.setDeleted(false);
		shipOwner.setPhoneNumber(userRequest.getPhoneNumber());
		shipOwner.setUserType(UserType.Instructor);
		ShipOwner saved = userRepository.save(shipOwner);
		RegistrationRequest request = registrationRequestRepository.save(new RegistrationRequest(userRequest.getEmail()));
        return saved;

	}

	@Override
	public CottageOwner saveCottageOwner(RegistrationRequestDto userRequest) {
		CottageOwner cottageOwner = new CottageOwner();
		cottageOwner.setFirstName(userRequest.getFirstName());
		cottageOwner.setLastName(userRequest.getLastName());
		cottageOwner.setAddress(new Address(userRequest.getStreetName(), userRequest.getStreetNumber(), userRequest.getCity(), userRequest.getCountry()));
		cottageOwner.setEmail(userRequest.getEmail());
		cottageOwner.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		cottageOwner.setDescriptionOfRegistration(userRequest.getDescriptionOfRegistration());
		cottageOwner.setActivated(false);
		cottageOwner.setDeleted(false);
		cottageOwner.setPhoneNumber(userRequest.getPhoneNumber());
		cottageOwner.setUserType(UserType.Instructor);
		CottageOwner saved = userRepository.save(cottageOwner);
		RegistrationRequest request = registrationRequestRepository.save(new RegistrationRequest(userRequest.getEmail()));
		return saved;
	}

	@Override
	public void activateAccount(String email) {
		User user = userRepository.findByEmail(email);
		user.setActivated(true);
		userRepository.save(user);
	}


}
