package com.example.demo.service;

import com.example.demo.entity.Recipe;

public interface RecipeDetailService {
	
	Recipe findByRecipeId(Integer recipeId);

}
