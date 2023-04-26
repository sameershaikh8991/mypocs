package com.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.model.User;
import com.security.repo.UserRepo;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findByUsername(username);
		user.orElseThrow(()-> new UsernameNotFoundException("Not found:" + username));
		return user.map(MyUserDetails::new).get();
	}
	
	public User saveuser(User user) {
		 return userRepo.save(user);
		
	}
	
	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
