package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Recipe;

public interface RecipeListService {
	List<Recipe> findByNameWildcard(String recipeName);
	
	

	

}
