package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.UserDTO;
import model.User;
import service.UserService;

@RestController
@RequestMapping(value = "api/users")

public class UserController {


	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<UserDTO>> getAllStudents() {

		List<User> users = userService.findAll();

		// convert students to DTOs
		List<UserDTO> usersDTO = new ArrayList<>();
		for (User u : users) {
			usersDTO.add(new UserDTO(u));
		}

		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}
	
	
}
