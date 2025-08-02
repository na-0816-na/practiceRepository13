package com.example.demo.repository;

import com.example.demo.entity.User;

public interface UserRepository {
	
	User findByEmail(String email); 

	void add(User user);

}



