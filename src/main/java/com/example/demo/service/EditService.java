package com.example.demo.service;

import com.example.demo.entity.Recipe;

public interface EditService {
 
	void edit(Recipe recipe);
	
	Recipe findById(Integer recipeId);

}
