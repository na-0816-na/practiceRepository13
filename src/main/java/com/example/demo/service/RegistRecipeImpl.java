package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RegistRecipeImpl implements RegistRecipe {
	
	private final RecipeRepository repository;

	@Override
	public void add(Recipe recipe) {
		
		repository.add(recipe);

	}

}
