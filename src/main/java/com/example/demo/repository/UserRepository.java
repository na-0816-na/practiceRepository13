package com.example.demo.repository;

import com.example.demo.entity.User;

public interface UserRepository {
	
	User findByEmail(String email); 
	
	String findUserNameById(Integer userId);

	void add(User user);
	
	 boolean existsByEmail(String email);

}



