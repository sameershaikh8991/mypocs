package com.neosoft.controller;

import com.neosoft.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.neosoft.model.User;
import com.neosoft.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private KafkaProducer producer;
	
	@PostMapping(value = "/saveUser")
	public User saveUser(@RequestBody User user) {
		User us = userService.saveUser(user);
		producer.sendMessage("user created "+user);
		return us;
	}
	@GetMapping(value = "/{userId}")
	public User getUser(@PathVariable int userId) {
    User us = userService.listByUserId(userId);
		producer.sendMessage("user get by "+userId);
		return us;
	}
	
	@DeleteMapping(value = "/deleteUser/{userId}")
	public void delete(@PathVariable int userId) {
		producer.sendMessage("user deleted by "+userId);
		userService.deleteUser(userId);
		
	}
	
	@PutMapping("/updateUser/{userId}")
	public User updateUser(@PathVariable int userId,@RequestBody User user) {
		user.setUserId(userId);
		producer.sendMessage("user updated for"+userId);
		return userService.updateUser(user);
	}

}
