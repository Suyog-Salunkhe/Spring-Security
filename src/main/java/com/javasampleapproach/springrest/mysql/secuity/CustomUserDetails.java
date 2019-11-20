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
	private boolean active;
	private List<GrantedAuthority> authorities;
	
	public CustomUserDetails(Users user) {
		this.userName = user.getUsername();
	    this.password = user.getPassword();
	    if(user.getActive()== 'A')
	    	this.active = true;
	    else
	    	this.active = false;
	    
	    this.authorities = Arrays.stream(user.getRoles().split(","))
	                    .map(SimpleGrantedAuthority::new)
	                    .collect(Collectors.toList());
	}
	 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return active;
	}
	
}
