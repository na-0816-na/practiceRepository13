package com.example.demo.service;

import com.example.demo.entity.Recipe;

public interface RegistRecipeService {
	
	void add(Recipe recipe);
	
	 Integer findCategoryIdByName(String categoryName);
}
