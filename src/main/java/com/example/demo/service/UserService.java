package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {

	void regist(User user);
	
	User findByEmail(String email);
	
	String findUserNameById(Integer userId);
	
	boolean existsByUserName(String userName);

    void registerUser(User user);

    boolean existsByEmail(String email);
}
	


