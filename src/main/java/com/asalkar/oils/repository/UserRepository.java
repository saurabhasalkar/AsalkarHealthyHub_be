package com.asalkar.oils.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asalkar.oils.model.User;
//import com.asalkar.oils.services.Users;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

	User findByEmail(String email);

	//User findByUsername(String username);
}
