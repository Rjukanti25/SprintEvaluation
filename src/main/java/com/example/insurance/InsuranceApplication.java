package com.example.insurance;
 


//import java.time.LocalDate;
// 
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
 
//import com.example.insurance.dto.UserDTO;
//import com.example.insurance.service.UserService;
 
@SpringBootApplication
public class InsuranceApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
	}
 
	//@Autowired
	//UserService service;
	
//	@Configuration
//	@EnableWebSecurity
//	class WebSecurityConfig {
// 
// 
//		@Bean
//	    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//	        http.csrf((csrf) -> csrf.disable())
//	                .authorizeHttpRequests((requests) -> requests
//	                        .requestMatchers("/insurance","/users").authenticated()
//	                        .requestMatchers(HttpMethod.POST, "/sigin","/add").permitAll())
//	                .formLogin(Customizer.withDefaults())
//	                .httpBasic(Customizer.withDefaults());
//	        return http.build();
//	    }
//
//	}
 
}