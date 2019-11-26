package com.javasampleapproach.springrest.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.springrest.mysql.repo.UsersRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	UsersRepository userRepository;
	
	@PostMapping("/go")
	public ResponseEntity<String> login(@RequestBody String username) {
		System.out.println("Welcome " + username);
		userRepository.findByUsername(username);
		return new ResponseEntity<String>(username, HttpStatus.OK);
	}
	
	@GetMapping("/user")
	public String user() {
		return "Welcome ";
	}
	
	@GetMapping("/")
	public String home() {
	    return ("<h1>Welcome</h1>");
	}

	@GetMapping("/admin")
	public String admin() {
	    return ("<h1>Welcome Admin</h1>");
	}
	    
}
