package com.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.model.User;

public interface UserRepo extends JpaRepository<User,Integer> {

	
	Optional<User> findByUsername(String usernanme);
	
}
