package com.example.insurance.test.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.insurance.controller.UserController;
import com.example.insurance.dto.UserDTO;
import com.example.insurance.entity.Users;
import com.example.insurance.mapper.UserMapper;
import com.example.insurance.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	    @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private UserService userService;

	    @Autowired
	    private ObjectMapper objectMapper;

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
    void should_return_user_list() throws Exception {
    	
        Users user = getDummyUser();
        UserMapper m=new UserMapper();
        UserDTO userDTO=m.UserstoUsersDTO(user);
        given(userService.getAllUsers()).willReturn(List.of(userDTO));
        mockMvc.perform(get("/insurance/users/all")
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk());
    }
    
    @Test
    void should_return_user()  throws Exception{
    	Users user = getDummyUser();
        UserMapper m=new UserMapper();
        UserDTO userDTO=m.UserstoUsersDTO(user);
        System.out.println(userDTO);
        given(userService.addUser(userDTO)).willReturn(userDTO);
        mockMvc.perform(post("/insurance/users/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userDTO)))
        .andExpect(status().isCreated());
    }
      
}
