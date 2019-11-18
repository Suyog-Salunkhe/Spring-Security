package com.javasampleapproach.springrest.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.springrest.mysql.repo.UsersRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UsersRepository userRepository;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody String username) {
		System.out.println("Welcome " + username);
		userRepository.findByUsername(username);
		return new ResponseEntity<String>(username, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>Welcome user <h1>";
	}
}
