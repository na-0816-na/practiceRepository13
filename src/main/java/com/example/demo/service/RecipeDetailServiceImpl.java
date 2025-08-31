package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeDetailServiceImpl implements RecipeDetailService {
	
	private final RecipeRepository recipeRepository;

    @Override
    public Recipe findByRecipeId(Integer recipeId) {
        return recipeRepository.findById(recipeId);
    }
}