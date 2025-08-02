package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Recipe;

public interface RecipeRepository {

	void add(Recipe recipe);
	
	List<Recipe> findByNameWildcard(String recipeName);
	
	void update(Recipe recipe);
	
	void delete(Recipe recipe);
}

