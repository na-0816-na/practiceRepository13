package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RemoveServiceImpl implements RemoveService {

	private final RecipeRepository repository;
	
	@Override
	public void remove(Recipe recipe) {
		
		repository.delete(recipe);

	
		
	}

}
