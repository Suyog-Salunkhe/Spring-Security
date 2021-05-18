package com.javasampleapproach.springrest.mysql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javasampleapproach.springrest.mysql.model.AuthenticationRequest;
import com.javasampleapproach.springrest.mysql.model.AuthenticationResponse;
import com.javasampleapproach.springrest.mysql.repo.UsersRepository;
import com.javasampleapproach.springrest.mysql.secuity.CustomUserDetailService;
import com.javasampleapproach.springrest.mysql.util.JwtUtil;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Autowired
	JwtUtil jwtTokenUtil;
	
	@PostMapping("/go")
	public ResponseEntity<String> login(@RequestBody String username) {
		System.out.println("Welcome " + username);
		return new ResponseEntity<String>(username, HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<?> user() {
		return ResponseEntity.ok(new String("Welcome admin"));
	}
	
	@GetMapping("/")
	public String home() {
	    return ("<h1>Welcome</h1>");
	}

	@GetMapping("/admin")
	public ResponseEntity<?> admin() {
	    return ResponseEntity.ok("<h1>Welcome Admin</h1>");
	}
	
	@PostMapping("/jwt")
	public ResponseEntity<?> authtest(@RequestBody AuthenticationRequest authRequest) throws Exception{
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		}catch (BadCredentialsException be) {
			return new ResponseEntity<>("Incorrect Username or password",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		final UserDetails userDetails =  customUserDetailService.loadUserByUsername(authRequest.getUsername());
		
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
		
	}
}
