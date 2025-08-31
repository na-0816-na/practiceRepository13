package com.example.demo.service;

import com.example.demo.entity.Recipe;

public interface RemoveService {
	
	void remove(Recipe recipe);
	
    Recipe findById(Integer recipeId); 

}
