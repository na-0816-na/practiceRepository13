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
	@Override
	public User findByEmail(String email) {
		
	    return repository.findByEmail(email);
	}

	@Override
	public String findUserNameById(Integer userId) {
		return repository.findUserNameById(userId);
	}
	
	 @Override
	    public void registerUser(User user) {
	        if (repository.existsByEmail(user.getEmail())) {
	            throw new IllegalArgumentException("このメールアドレスは既に使われています");
	        }
	        repository.add(user);
	    }

	    @Override
	    public boolean existsByEmail(String email) {
	        return repository.existsByEmail(email);
	    }
	}



