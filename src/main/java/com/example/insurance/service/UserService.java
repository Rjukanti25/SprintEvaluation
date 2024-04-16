package com.example.insurance.service;

import java.util.List;

import com.example.insurance.dto.UserDTO;

public interface UserService {
	public UserDTO addUser(UserDTO userDTO);
	public UserDTO updateUser(int userId,UserDTO userDTO);
	public UserDTO getUserById(int userId);
	public UserDTO getUserByEmail(String email);
	public List<UserDTO> getAllUsers();
	public UserDTO signIn(String userName, String Password);
	public boolean deleteUser(int userId);
}
