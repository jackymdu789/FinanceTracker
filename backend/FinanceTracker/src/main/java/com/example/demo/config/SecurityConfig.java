package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.userrole.UserInfoDetailsServices;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
//		UserDetails admin = User.withUsername("admin").password("1234").roles("Admin").build();
//		UserDetails user = User.withUsername("user").password("1234").roles("User").build();
//		return new InMemoryUserDetailsManager(user, admin);
		return new UserInfoDetailsServices();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    return http
	            .csrf().disable()
	            .authorizeHttpRequests(authorizeRequests ->
	                authorizeRequests
	                    .requestMatchers("/api/v1/userinfo/**").permitAll()
	                    .requestMatchers("/api/v1/userdetails/all").hasRole("ADMIN")
	                    .requestMatchers("**").hasAnyRole("USER", "ADMIN")
	            )
	            .formLogin()
	            .and()
	            .build();
	}


	@Bean
	public PasswordEncoder passwordEncorder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider  authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncorder());
		return authenticationProvider;
	}

}
