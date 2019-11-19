package com.javasampleapproach.springrest.mysql.secuity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.javasampleapproach.springrest.mysql.model.Users;

public class CustomUserDetails implements UserDetails {
	
	private String userName;
	private String password;
	private char active;
	private List<GrantedAuthority> authorities;
	
	public CustomUserDetails(Users user) {
		this.userName = user.getUsername();
	    this.password = user.getPassword();
	    this.active = user.getActive();
	    this.authorities = Arrays.stream(user.getRoles().split(","))
	                    .map(SimpleGrantedAuthority::new)
	                    .collect(Collectors.toList());
	}
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.getAuthorities();
	}

	@Override
	public String getPassword() {
		
		return this.getPassword();
	}

	@Override
	public String getUsername() {
		
		return this.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return false;
	}

	@Override
	public boolean isEnabled() {
		
		return false;
	}
	
}
