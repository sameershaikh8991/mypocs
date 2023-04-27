package com.neosoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosoft.model.User;
import com.neosoft.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User saveUser(User user) {

		return userRepository.save(user);
	}

	public List<User> listAll() {

		return userRepository.findAll();
	}

	public User listByUserId(Integer userId) {

		return userRepository.findById(userId).get();
	}

	public void deleteUser(Integer userId) {

		userRepository.deleteById(userId);
	}
	
	public User updateUser(User user) {
		
		return userRepository.save(user);
	}

}
