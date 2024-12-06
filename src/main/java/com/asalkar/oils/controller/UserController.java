package com.asalkar.oils.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.asalkar.dto.*;
import com.asalkar.dto.LoginResponse;
import com.asalkar.dto.utility.JwtUtils;
import com.asalkar.oils.model.User;
import com.asalkar.oils.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	public UserService userservice;
	
	@Autowired
	public PasswordEncoder passwordencoder;
	
	@Autowired
    private JwtUtils jwtUtils;
	
	@PostMapping("api/registeruser")
	public ResponseEntity<?> RegisterUser(@RequestBody User user)
	{   
		try {
		 userservice.RegisterUser(user);
		return ResponseEntity.ok("Registration Successful");
		
	} catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(500).body("An error occurred during registration.");
    }
		
	}
	
	@PostMapping("/api/login")
	public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
	    System.out.println("Login request received: " + loginRequest);

	    // Find user by email
	    User user = userservice.FindByEmail(loginRequest.getEmail());
	    if (user == null) {
	        throw new RuntimeException("User not found");
	    }

	    // Validate password
	    if (!passwordencoder.matches(loginRequest.getPassword(), user.getPassword())) {
	        throw new RuntimeException("Invalid credentials");
	    }

	    // Generate JWT token
	    String token = jwtUtils.generateToken(user.getEmail());

	    // Return a structured response with user details
	    return new LoginResponse(
	        "success", // Status of the response
	        "Login successful", // Success message
	        new UserDTO(user.getId(), user.getFullName(), user.getEmail()), // User details
	        token // JWT token
	    );
	}


}