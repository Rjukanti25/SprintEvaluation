package com.example.insurance;
 
import java.time.LocalDate;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import com.example.insurance.dto.UserDTO;
import com.example.insurance.service.UserService;
 
@SpringBootApplication
public class InsuranceApplication {
 
	public static void main(String[] args) {
		SpringApplication.run(InsuranceApplication.class, args);
	}
 
	@Autowired
	UserService service;


 
}