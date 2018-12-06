package com.stone.spring.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stone.spring.annotation.repository.UserRepository;

@Service
public class UserService {	
	@Autowired
	private UserRepository userRepository;
	public void add() {
		System.out.println("UserService add...");
		userRepository.save();
	}
}