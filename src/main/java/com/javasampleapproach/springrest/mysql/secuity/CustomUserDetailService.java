package com.javasampleapproach.springrest.mysql.secuity;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.javasampleapproach.springrest.mysql.model.Users;
import com.javasampleapproach.springrest.mysql.repo.UsersRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private WebApplicationContext applicationContext;
	 
	public CustomUserDetailService() {
	        super();
	}
	  
    @PostConstruct
    public void completeSetup() {
        userRepository = applicationContext.getBean(UsersRepository.class);
    }
    
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
    	List<Users> users = userRepository.findByUsername(username);
		
		if (users == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(users.get(0));   
	}

}
