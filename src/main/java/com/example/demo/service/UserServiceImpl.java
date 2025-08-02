package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	
	@Override
	public void regist(User user) {
		
		repository.add(user);

	}
	
	public User findByEmail(String email) {
	    return repository.findByEmail(email);
	}
	}


