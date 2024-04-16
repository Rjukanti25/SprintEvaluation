package com.example.insurance.test.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.example.insurance.dto.UserDTO;
import com.example.insurance.entity.Users;
import com.example.insurance.mapper.UserMapper;
import com.example.insurance.repository.UsersRepository;
import com.example.insurance.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UsersRepository userRepo;
	
	

	@Mock
	UserMapper userMapper;
	
	
	@InjectMocks
	private UserServiceImpl userService;
	
	public Users getDummyUser() {
		Users user=new Users();
		user.setUserId(1);
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
	        // Given
	        Users user = this.getDummyUser();
	        
	        // When
	        when(userRepo.findAll()).thenReturn(List.of(user));
	        List<UserDTO> users = this.userService.getAllUsers();
	        // Then
	        Assertions.assertEquals(1, users.size());
	        verify(this.userRepo).findAll();
	    }
	  @Test
	    void getUserById_should_return_user() {
	        // Given
	        Users user = getDummyUser();
	        UserMapper m = new UserMapper();
	        UserDTO d = m.UserstoUsersDTO(user);
	        
	        // Mock repository behavior
	        when(userRepo.findById(1)).thenReturn(Optional.of(user));
	        when(userMapper.UserstoUsersDTO(user)).thenReturn(d);
	        
	        // When
	        
	        UserDTO returnedUser = userService.getUserById(1);
	        
	        // Then
	        Assertions.assertEquals(user.getUserId(), returnedUser.getUserId());
	        verify(userRepo).findById(1);
	    }
	   
	  @Test
	  void addUser_should_return_added_user() {
	      // Given
	      Users user = getDummyUser();
	      UserMapper m=new UserMapper();
	      UserDTO userDTO = m.UserstoUsersDTO(user);
	      // Mock repository behavior
	      when(userMapper.userDTOtoUser(userDTO)).thenReturn(user);
	      when(userRepo.save(user)).thenReturn(user);
	      when(userMapper.UserstoUsersDTO(user)).thenReturn(userDTO);

	      // When
	      UserDTO addedUserDTO = userService.addUser(userDTO);

	      // Then
	      Assertions.assertNotNull(addedUserDTO); // Ensure addedUserDTO is not null
	      Assertions.assertEquals(user.getUserId(), addedUserDTO.getUserId());
	      verify(userMapper).userDTOtoUser(userDTO);
	      verify(userRepo).save(user);
	      verify(userMapper).UserstoUsersDTO(user);
	  }

	    
	    @Test
	    void updateUser_should_return_updated_user() {
	        // Given
	        Users user = getDummyUser();
	        UserMapper m=new UserMapper();
	        UserDTO updatedUserDTO = m.UserstoUsersDTO(user);


		     // Mock repository behavior
//		     when(userMapper.userDTOtoUser(updatedUserDTO)).thenReturn(user);
		     when(userRepo.save(user)).thenReturn(user);
		     when(userMapper.UserstoUsersDTO(user)).thenReturn(updatedUserDTO);
	         when(userRepo.findById(1)).thenReturn(Optional.of(user));
	   

	        // When
	        UserDTO returnedUpdatedUserDTO = userService.updateUser(1, updatedUserDTO);

	        // Then
	        Assertions.assertEquals(user.getUserId(), returnedUpdatedUserDTO.getUserId());
	        verify(userRepo).findById(1);
	        verify(userRepo).save(user);
	    }
	    
	    @Test
	    void deleteUser_should_return_true() {
	        // Given
	    	
	        Users user = getDummyUser();
	        when(userRepo.findById(1)).thenReturn(Optional.of(user));

	        // When
	        boolean isDeleted=userService.deleteUser(1);

	        // Then
	        Assertions.assertTrue(isDeleted);
	        verify(userRepo).findById(1);
	        verify(userRepo).delete(user);
	    }
	    
	

}
