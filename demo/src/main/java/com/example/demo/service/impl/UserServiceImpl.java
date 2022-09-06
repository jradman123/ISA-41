package com.example.demo.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.*;
import com.example.demo.model.Address;
import com.example.demo.model.adventures.Adventure;
import com.example.demo.model.adventures.AdventureReservation;
import com.example.demo.model.cottages.Cottage;
import com.example.demo.model.cottages.CottageReservation;
import com.example.demo.model.enumeration.AdminType;
import com.example.demo.model.enumeration.UserType;
import com.example.demo.model.reservation.Reservation;
import com.example.demo.model.ships.Ship;
import com.example.demo.model.ships.ShipReservation;
import com.example.demo.model.users.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RegistrationRequestRepository registrationRequestRepository;

	@Autowired
	private RegistrationForClientsServiceImpl registrationForClientsService;

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private CottageService cottageService;
	@Autowired
	private AdventureService adventureService;
	@Autowired
	private ShipService shipService;
	@Autowired
	private CottageRepository cottageRepository;
	@Autowired
	private ShipRepository shipRepository;
	@Autowired
	private AdventureRepository adventureRepository;


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
		instructor.setAddress(new Address(userRequest.getStreetName(),userRequest.getStreetNumber(),userRequest.getCity(),userRequest.getCountry(),userRequest.getLongitude(),userRequest.getLatitude()));
		instructor.setEmail(userRequest.getEmail());
		instructor.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		instructor.setDescriptionOfRegistration(userRequest.getDescriptionOfRegistration());
		instructor.setActivated(false);
		instructor.setDeleted(false);
		instructor.setPhoneNumber(userRequest.getPhoneNumber());
		instructor.setUserType(UserType.Instructor);
		Instructor saved = userRepository.save(instructor);
		registrationRequestRepository.save(new RegistrationRequest(userRequest.getEmail()));
		return saved;
	}

	@Override
	public ShipOwner saveShipOwner(RegistrationRequestDto userRequest) {
		ShipOwner shipOwner = new ShipOwner();
		shipOwner.setFirstName(userRequest.getFirstName());
		shipOwner.setLastName(userRequest.getLastName());
		shipOwner.setAddress(new Address(userRequest.getStreetName(),userRequest.getStreetNumber(),userRequest.getCity(),userRequest.getCountry(),userRequest.getLongitude(),userRequest.getLatitude()));
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
		cottageOwner.setAddress(new Address(userRequest.getStreetName(), userRequest.getStreetNumber(), userRequest.getCity(), userRequest.getCountry(),userRequest.getLongitude(),userRequest.getLatitude()));
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
	public RegisteredUser saveRegisteredUser(RegistrationRequestDto userRequest) throws UnknownHostException {
		RegisteredUser registeredUser = new RegisteredUser();
		registeredUser.setFirstName(userRequest.getFirstName());
		registeredUser.setLastName(userRequest.getLastName());
		registeredUser.setAddress(new Address(userRequest.getStreetName(), userRequest.getStreetNumber(), userRequest.getCity(), userRequest.getCountry(),userRequest.getLongitude(),userRequest.getLatitude()));
		registeredUser.setEmail(userRequest.getEmail());
		registeredUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		registeredUser.setActivated(false);
		registeredUser.setDeleted(false);
		registeredUser.setPhoneNumber(userRequest.getPhoneNumber());
		registeredUser.setUserType(UserType.Client);
		registeredUser.setDescriptionOfRegistration("---");
		RegisteredUser saved = userRepository.save(registeredUser);
		RegisteredUser saved2 = registrationForClientsService.sendVerificationEmail(saved);
		//RegistrationRequest request = registrationRequestRepository.save(new RegistrationRequest(userRequest.getEmail()));

		return saved;
	}

	public Administrator saveAdministrator(AdministratorRegistrationDto administratorRegistrationDto) {
		Administrator administrator = new Administrator();
		administrator.setFirstName(administratorRegistrationDto.getFirstName());
		administrator.setLastName(administratorRegistrationDto.getLastName());
		administrator.setAddress(new Address(administratorRegistrationDto.getStreetName(), administratorRegistrationDto.getStreetNumber(),
								administratorRegistrationDto.getCity(), administratorRegistrationDto.getCountry(),administratorRegistrationDto.getLongitude(),administratorRegistrationDto.getLatitude()));
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
				user.getPhoneNumber(),user.getEmail(),user.getAddress().getLongitude(),user.getAddress().getLatitude());
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
		user.getAddress().setLongitude(data.getLongitude());
		user.getAddress().setLatitude(data.getLatitude());
		User saved = save(user);
		return new PersonalData(saved.getFirstName(),saved.getLastName(),saved.getAddress().getStreetNumber(),saved.getAddress().getStreetName(),
				saved.getAddress().getCity(),saved.getAddress().getCountry(),saved.getPhoneNumber(),saved.getEmail(),saved.getAddress().getLongitude(),saved.getAddress().getLatitude());
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

	@Override
	public boolean isPredefAdmin(String email) {
		Administrator admin = (Administrator) findByEmail(email);
		if(admin.getType().equals(AdminType.Predef)){
			return true;
		}
		return false;
	}

	@Override
	public void deleteUser(User user) {
		deleteUser(userRepository.findByEmail(user.getEmail()).getId());

	}

	@Override

	public InstructorPersonalData getInstructorPersonalData(String email) {
		Instructor instructor = (Instructor) findByEmail(email);
		return new InstructorPersonalData(instructor.getFirstName(),instructor.getLastName(),instructor.getAddress().getStreetNumber(),
				instructor.getAddress().getStreetName(),instructor.getAddress().getCity(),instructor.getAddress().getCountry(),
				instructor.getPhoneNumber(),instructor.getEmail(),instructor.getBiography(),instructor.getAddress().getLongitude(),instructor.getAddress().getLatitude());
	}

	@Override
	public InstructorPersonalData updateInstructorPersonalData(InstructorPersonalData data, String email) {
		Instructor instructor = (Instructor) findByEmail(email);
		instructor.setFirstName(data.getFirstName());
		instructor.setLastName(data.getLastName());
		instructor.setPhoneNumber(data.getPhoneNumber());
		instructor.getAddress().setStreetName(data.getStreetName());
		instructor.getAddress().setStreetNumber(data.getStreetNumber());
		instructor.getAddress().setCity(data.getCity());
		instructor.getAddress().setCountry(data.getCountry());
		instructor.getAddress().setLongitude(data.getLongitude());
		instructor.getAddress().setLatitude(data.getLatitude());
		instructor.setBiography(data.getBiography());
		Instructor saved = userRepository.save(instructor);
		return new InstructorPersonalData(saved.getFirstName(),saved.getLastName(),saved.getAddress().getStreetNumber(),saved.getAddress().getStreetName(),
				saved.getAddress().getCity(),saved.getAddress().getCountry(),saved.getPhoneNumber(),saved.getEmail(),saved.getBiography(),saved.getAddress().getLongitude(),saved.getAddress().getLatitude()
		);
	}

	public List<PersonalData> findAllUsers() {
		return null;
	}

	@Override
	public List<User> getAll() {
		List<User> all = new ArrayList<>();
		for(User user : userRepository.findAllUndeleted()){
			all.add(reservationService.updatePoints(user));
		}
		return all;
	}

	@Override
	public User deleteUser(int id) {
		UserType type = userRepository.findById(id).get().getUserType();
		if(type.equals(UserType.CottageAdvertiser)){
			for(CottageReservation res : reservationService.getByCottageOwner(id)) {
				res.setDeleted(true);
				reservationRepository.save(res);
			}
			for(CottageDto cottageDto : cottageService.getOwnerCottages(userRepository.findById(id).get().getEmail())){
				Cottage cottage = cottageRepository.findById(Long.parseLong(cottageDto.getId())).get();
				cottage.setDeleted(true);
				cottageRepository.save(cottage);
			}
		}
		else if(type.equals(UserType.ShipAdvertiser)){
			for(ShipReservation res : reservationService.getByShipOwner(id)){
				res.setDeleted(true);
				reservationRepository.save(res);
			}

			for(ShipDto shipDto : shipService.getOwnerShips(userRepository.findById(id).get().getEmail())){
				Ship ship = shipRepository.findById(Long.parseLong(shipDto.getId())).get();
				ship.setDeleted(true);
				shipRepository.save(ship);
			}
		}else if(type.equals(UserType.Instructor)){
			for(AdventureReservation res : reservationService.getByInstructor(id)){
				res.setDeleted(true);
				reservationRepository.save(res);
			}
			for(Adventure adventure : adventureRepository.getAllInstructors(userRepository.findById(id).get().getId())){
				adventureService.deleteAdventure(adventure.getId());
			}
		}else if(type.equals(UserType.Client)){
			for(Reservation res : reservationService.getByUser(id)){
				res.setDeleted(true);
				reservationRepository.save(res);
			}
		}

		User userDb = userRepository.findById(id).get();
		userDb.setDeleted(true);
		return userRepository.save(userDb);
	}


}
