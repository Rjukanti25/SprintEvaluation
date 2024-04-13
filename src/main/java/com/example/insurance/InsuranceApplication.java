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
	
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		UserDTO user= new UserDTO();
		user.setUsername("Akash27");
		user.setPassword("12345678");
		user.setFirstName("Akash");
		user.setLastName("Tumma");
		user.setEmail("tummaAkash@gmail.com");
		user.setDateOfBirth(LocalDate.now());
		user.setAddress("Uppal");
		user.setCity("Hyderabad");
		user.setState("Telangana");
        user.setZipCode("500039");		
        service.addUser(user);
	}

 
}