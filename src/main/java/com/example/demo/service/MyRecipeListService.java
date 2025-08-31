package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Recipe;

public interface MyRecipeListService {
	
	List<Recipe> getMyRecipe(Integer userId);

}
