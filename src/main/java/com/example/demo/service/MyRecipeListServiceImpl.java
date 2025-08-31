package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyRecipeListServiceImpl implements MyRecipeListService {

	private final RecipeRepository recipeRepository;

    @Override
    public List<Recipe> getMyRecipe(Integer userId) {
        return recipeRepository.findByUserId(userId);
    }
}
