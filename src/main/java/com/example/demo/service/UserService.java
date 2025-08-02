package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

	void regist(User user);
	
	User findByEmail(String email);
	}
	


