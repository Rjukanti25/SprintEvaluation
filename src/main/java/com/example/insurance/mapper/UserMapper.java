package com.example.insurance.mapper;
 
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
 
import com.example.insurance.dto.UserDTO;
import com.example.insurance.entity.Users;
 
@Component
public class UserMapper {
	public Users userDTOtoUser(UserDTO dto) {
		Users user = new Users();
		user.setUserId(dto.getUserId());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setDateOfBirth(dto.getDateOfBirth());
		user.setAddress(dto.getAddress());
		user.setCity(dto.getCity());
		user.setState(dto.getState());
		user.setZipCode(dto.getZipCode());
		return user;
	}
	public UserDTO UserstoUsersDTO (Users user) {
		UserDTO dto = new UserDTO();
		dto.setUserId(user.getUserId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setDateOfBirth(user.getDateOfBirth());
		dto.setAddress(user.getAddress());
		dto.setCity(user.getCity());
		dto.setState(user.getState());
		dto.setZipCode(user.getZipCode());
		return dto;
	}
//	public String getPassword()
//	{
//		return "**********";
//	}
}