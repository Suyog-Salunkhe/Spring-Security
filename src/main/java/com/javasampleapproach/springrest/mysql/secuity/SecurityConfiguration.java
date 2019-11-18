package com.javasampleapproach.springrest.mysql.secuity;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;

	@Autowired
    private WebApplicationContext applicationContext;

	@Autowired
	private AuthenticationSuccessHandlerImpl successHandler;
	 
    private CustomUserDetailService userDetailService;
    
	/*@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
				"select username,password, enabled from users where username=?")
			.authoritiesByUsernameQuery(
				"select username, role from user_roles where username=?");
	}
	*/

	@PostConstruct
    public void completeSetup() {
        userDetailService = applicationContext.getBean(CustomUserDetailService.class);
    }
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailService)
	            .passwordEncoder(passwordEncoder())
	            .and()
	            .authenticationProvider(authenticationProvider())
	            .jdbcAuthentication()
	            .dataSource(dataSource);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	      web.ignoring()
	            .antMatchers("/resources/**");
	}
	    
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}
	  
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
         .antMatchers("/api/login")
         .permitAll()
         .and()
         .formLogin()
         .permitAll()
         .successHandler(successHandler)
         .and()
         .csrf()
         .disable();
		 
		/*http.authorizeRequests()
		.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
		.and()
		  .formLogin().loginPage("/login").failureUrl("/login?error")
		  .usernameParameter("username").passwordParameter("password")
		.and()
		  .logout().logoutSuccessUrl("/login?logout")
		.and()
		  .exceptionHandling().accessDeniedPage("/403")
		.and()
		  .csrf();*/
		
	}
}
