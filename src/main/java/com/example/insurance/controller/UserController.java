package com.example.insurance.controller;

import com.example.insurance.dto.UserDTO;
import com.example.insurance.entity.Users;
import com.example.insurance.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
//import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/insurance/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        // Add a new user
        UserDTO addedUser = userService.addUser(userDTO);
        return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable int userId) {
        // Get user by ID
        UserDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int userId, @RequestBody UserDTO userDTO) {
        // Update user by ID
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        // Get user by email
        UserDTO user = userService.getUserByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // Get all users
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserDTO> signIn(@RequestParam String username, @RequestParam String password) {
        // Sign in with username and password
        UserDTO user = userService.signIn(username, password);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
        
//    	String token =getJWTToken(username);
//    	Users user=new Users();
//    	user.setUsername(username);
//    	user.setToken(token);
//    	return new ResponseEntity<Users>(user,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        // Delete user by ID
        boolean deleted = userService.deleteUser(userId);
        if (deleted) {
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    private String getJWTToken(String username) {
//		String secretKey = "mySecretKey";
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//				.commaSeparatedStringToAuthorityList("ROLE_USER");
//		String token = Jwts
//				.builder()
//				.setId("softtekJWT")
//				.setSubject(username)
//				.claim("authorities",
//						grantedAuthorities.stream()
//								.map(GrantedAuthority::getAuthority)
//								.collect(Collectors.toList()))
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 600000))
//				.signWith(SignatureAlgorithm.HS512,
//						secretKey.getBytes()).compact();
// 
//		return "Bearer " + token;
//	}
}
