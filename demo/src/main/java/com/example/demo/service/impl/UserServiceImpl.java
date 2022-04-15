package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.dto.RegistrationRequestDto;
import com.example.demo.model.Address;
import com.example.demo.model.enumeration.UserType;
import com.example.demo.model.users.Instructor;
import com.example.demo.model.users.RegistrationRequest;
import com.example.demo.repository.RegistrationRequestRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.users.User;
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
		instructor.setAddress(userRequest.getAddress());
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
}
