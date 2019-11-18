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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UsersRepository repository;

	@PostMapping("/y")
	public ResponseEntity<String> login(@RequestBody String username) {
		System.out.println("Welcome " + username);
		return new ResponseEntity<String>(username, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public String user() {
		return "<h1>Welcome user <h1>";
	}

	
	@PostMapping("/login")
	public String admin() {
		return "<h1>Welcome admin<h1>";
	}
	

}