package com.asalkar.oils.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.asalkar.oils.model.User;
import com.asalkar.oils.repository.UserRepository;

@Service
public class UserService {
	
	
	@Autowired
	public UserRepository userrepository;
	
	@Autowired
	public PasswordEncoder passwordencoder;
	
	public User RegisterUser(User user){
		
		if(userrepository.existsByEmail(user.getEmail())) {
			
			throw new IllegalArgumentException("Email already in use.");
		}
		
		user.setPassword(passwordencoder.encode(user.getPassword()));
		
		return userrepository.save(user);
	}
	
	public User FindByEmail(String email) {
      
		 return userrepository.findByEmail(email);
	}
	
	
 
}
