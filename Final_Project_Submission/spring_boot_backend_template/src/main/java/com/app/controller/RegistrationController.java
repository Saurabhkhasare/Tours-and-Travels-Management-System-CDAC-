package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojo.User;
import com.app.service.UserService;

@RestController()
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//to redirect user to register form
	@GetMapping
    public String showRegistrationForm() {
        return "register";
    }

	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		try {
			User newuser = userService.addUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
		}

	}

}
