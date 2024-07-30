//package com.example.demo.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//	@Bean
////	authecation
//	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
//		UserDetails admin = User.withUsername("admin").password(encoder.encode("1234")).roles("Admin").build();
//		UserDetails user = User.withUsername("user").password(encoder.encode("1234")).roles("User").build();
//		return new InMemoryUserDetailsManager(user, admin);
//	}
//
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.csrf().disable()
//					.authorizeHttpRequests().requestMatchers("/api/v1/userdetails").permitAll()
//					.and()
//					.authorizeHttpRequests().requestMatchers("/api/v1/userdetails/all").hasRole("Admin")
//					.and()
//					.authorizeHttpRequests().requestMatchers("/**").authenticated()
//					.and().formLogin()
//					.and().build();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncorder() {
//		return new BCryptPasswordEncoder();
//	}
//}
