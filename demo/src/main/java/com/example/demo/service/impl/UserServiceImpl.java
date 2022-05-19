package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.dto.AdministratorRegistrationDto;
import com.example.demo.dto.ChangePasswordDto;
import com.example.demo.dto.PersonalData;
import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.model.Address;
import com.example.demo.model.enumeration.AdminType;
import com.example.demo.model.enumeration.UserType;
import com.example.demo.model.users.*;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		instructor.setActivated(false);
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
		shipOwner.setActivated(true);
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
		cottageOwner.setActivated(true);
		cottageOwner.setDeleted(false);
		cottageOwner.setPhoneNumber(userRequest.getPhoneNumber());
		cottageOwner.setUserType(UserType.Instructor);
		CottageOwner saved = userRepository.save(cottageOwner);
		RegistrationRequest request = registrationRequestRepository.save(new RegistrationRequest(userRequest.getEmail()));
		return saved;
	}

	@Override
	public Administrator saveAdministrator(AdministratorRegistrationDto administratorRegistrationDto) {
		Administrator administrator = new Administrator();
		administrator.setFirstName(administratorRegistrationDto.getFirstName());
		administrator.setLastName(administratorRegistrationDto.getLastName());
		administrator.setAddress(new Address(administratorRegistrationDto.getStreetName(), administratorRegistrationDto.getStreetNumber(),
								administratorRegistrationDto.getCity(), administratorRegistrationDto.getCountry()));
		administrator.setEmail(administratorRegistrationDto.getEmail());
		administrator.setPassword(passwordEncoder.encode(administratorRegistrationDto.getPassword()));
		administrator.setActivated(true);
		administrator.setDeleted(false);
		administrator.setPhoneNumber(administratorRegistrationDto.getPhoneNumber());
		administrator.setUserType(UserType.Admin);
		administrator.setType(AdminType.Ordinary);
		administrator.setFirstLogin(true);
		Administrator saved = userRepository.save(administrator);
		return saved;
	}

	@Override
	public void activateAccount(String email) {
		User user = userRepository.findByEmail(email);
		user.setActivated(true);
		userRepository.save(user);
	}

	@Override
	public PersonalData getPersonalData(String email) {
		User user = findByEmail(email);
		return new PersonalData(user.getFirstName(),user.getLastName(),user.getAddress().getStreetNumber(),
				user.getAddress().getStreetName(),user.getAddress().getCity(),user.getAddress().getCountry(),
				user.getPhoneNumber(),user.getEmail());
	}

	@Override
	public PersonalData updatePersonalData(PersonalData data,String email) {
		User user = findByEmail(email);
		user.setFirstName(data.getFirstName());
		user.setLastName(data.getLastName());
		user.setPhoneNumber(data.getPhoneNumber());
		user.getAddress().setStreetName(data.getStreetName());
		user.getAddress().setStreetNumber(data.getStreetNumber());
		user.getAddress().setCity(data.getCity());
		user.getAddress().setCountry(data.getCountry());
		User saved = save(user);
		return new PersonalData(saved.getFirstName(),saved.getLastName(),saved.getAddress().getStreetNumber(),saved.getAddress().getStreetName(),
				saved.getAddress().getCity(),saved.getAddress().getCountry(),saved.getPhoneNumber(),saved.getEmail());
	}

	@Override
	public void changePassword(String email, ChangePasswordDto changePasswordDto) {
		User user = findByEmail(email);
		if(user.getUserType().equals(UserType.Admin)){
			Administrator admin = (Administrator) user;
			admin.setFirstLogin(false);
		}
		if (passwordEncoder.matches(changePasswordDto.getCurrentPassword(),user.getPassword())) {
			user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
		}

		userRepository.save(user);
	}

	@Override
	public boolean isFirstLogin(String email) {
		Administrator admin = (Administrator) findByEmail(email);
		return admin.isFirstLogin();
	}


}
