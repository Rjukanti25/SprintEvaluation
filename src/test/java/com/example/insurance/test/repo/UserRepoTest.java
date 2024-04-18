package com.example.insurance.test.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.insurance.entity.Users;
import com.example.insurance.repository.UsersRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepoTest {
	
	@Autowired
	UsersRepository userRepo;
	
	public Users getDummyUser() {
		Users user=new Users();
		user.setFirstName("Debyansh");
		user.setLastName("Shaw");
		user.setDateOfBirth(LocalDate.now());
		user.setAddress("Hawda");
		user.setCity("Kolkata");
		user.setState("kolkata");
		user.setZipCode("500039");
		user.setEmail("debyashshaw@gmail.com");
		user.setUsername("debyansh");
		user.setPassword("1234567890");
		return user;
	}
	
	@Test
    void findAll_should_return_users_list() {
        // When
        List<Users> employees = this.userRepo.findAll();
        // Then
        Assertions.assertEquals(5, employees.size());
    }
	
	@Test
    void findById_should_return_user() {
        // When
        Optional<Users> employee = this.userRepo.findById(2);
        // Then
        Assertions.assertTrue(employee.isPresent());
    }
	@Test
    void save_should_insert_new_user() {
        // Given
		Users user = getDummyUser();
        // When
        Users newUser = userRepo.save(user);
        // Then
        Assertions.assertNotNull(newUser);
        Assertions.assertEquals(user.getFirstName(), newUser.getFirstName());
    }
	
	@Test
    void save_should_update_existing_employee() {
        // Given
        Users user = new Users();
        user.setUserId(3);
        user.setFirstName("FIRST_NAME");
        user.setLastName("LAST_NAME");
        // When
        Users updateduser = this.userRepo.save(user);
        // Then
        Assertions.assertNotNull(updateduser);
        Assertions.assertEquals("FIRST_NAME", updateduser.getFirstName());
        Assertions.assertEquals("LAST_NAME", updateduser.getLastName());
    }
		@Test
	     void deleteById_should_delete_employee() {
	         // When
	         this.userRepo.deleteById(2);
	         Optional<Users> employee = this.userRepo.findById(2);
	         // Then
	         Assertions.assertFalse(employee.isPresent());
	     }

}
