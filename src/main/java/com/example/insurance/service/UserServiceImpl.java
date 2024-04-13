package com.example.insurance.service;
 
import java.util.List;
import java.util.stream.Collectors;
 
import org.springframework.stereotype.Service;
 
import com.example.insurance.dto.UserDTO;
import com.example.insurance.entity.Users;
import com.example.insurance.mapper.UserMapper;
import com.example.insurance.repository.UsersRepository;
 
import lombok.AllArgsConstructor;
 
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
 
	UsersRepository userRepo;
	UserMapper userMapper;
 
	@Override
	public UserDTO addUser(UserDTO userdto) {
		Users user=userMapper.userDTOtoUser(userdto);
		return userMapper.UserstoUsersDTO(userRepo.save(user));
	}

	@Override
    public UserDTO getUserById(int userId) {
        // Find the user by user ID
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));
 
        // Map User entity to UserDTO and return
        return userMapper.UserstoUsersDTO(user);
    }
	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		Users existingUser = userRepo.findById(userDTO.getUserId())
				.orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userDTO.getUserId()));
		Users updatedUser = userRepo.save(existingUser);
 
	    // Map updated User entity back to UserDTO
	    return userMapper.UserstoUsersDTO(updatedUser);
	}
	@Override
    public UserDTO getUserByEmail(String email) {
        // Find the user by email
        Users user = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
 
        // Map User entity to UserDTO and return
        return userMapper.UserstoUsersDTO(user);
    }
 
	@Override
    public List<UserDTO> getAllUsers() {
        // Retrieve all users from the repository
        List<Users> users = userRepo.findAll();
 
        // Map each User entity to UserDTO using UserMapper
        return users.stream()
                .map(userMapper::UserstoUsersDTO)
                .collect(Collectors.toList());
    }
	@Override
    public UserDTO signIn(String userName, String password) {
        // Find the user by username
        Users user = userRepo.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + userName));
 
        // Check if the provided password matches the user's password
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password for user: " + userName);
        }
 
        // Map User entity to UserDTO and return
        return userMapper.UserstoUsersDTO(user);
    }
}

