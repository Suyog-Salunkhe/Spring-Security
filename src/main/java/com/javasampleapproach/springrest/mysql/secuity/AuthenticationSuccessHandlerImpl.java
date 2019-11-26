package com.javasampleapproach.springrest.mysql.secuity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.javasampleapproach.springrest.mysql.repo.UsersRepository;

@Component
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

	 	@Autowired
	    private UsersRepository usersRepository;

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication arg2) throws IOException, ServletException {
	    	System.out.println("Inside Success handler");
	        //usersRepository.updateLastLogin(new Date());
	    	//response.sendRedirect(request.getHeader("referer"));
	    }
	    
	    @Override
	    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {

	    	return (String) request.getParameter("redirect");
	    	//return super.determineTargetUrl(request, response);
	    }
	    /*@Override
	    public AuthenticationSuccessHandler successHandler() {
	    	
	    	return new AuthenticationSuccessHandler() {
				
				@Override
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						Authentication authentication) throws IOException, ServletException {
					// TODO Auto-generated method stub
					
				}
			};
	    }*/
}
