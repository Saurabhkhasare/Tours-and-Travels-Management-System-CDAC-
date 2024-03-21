package com.app.controller;

import javax.servlet.http.HttpSession;

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

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String login() {
		return "/login";
	}

	@PostMapping
	public ResponseEntity<Object> loginUser(@RequestBody User user) {
		try {

			User newuser = userService.findUser(user);

			if (newuser == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Invalid email or password");
			}
			// session.setAttribute("userId", newuser.getId());
			return ResponseEntity.status(HttpStatus.OK).body(newuser);

		} catch (Exception e) {

		}
		return null;
	}

}
