package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service
public class EditServiceImpl implements EditService {

		private final RecipeRepository repository;
		
		@Override
		public void edit(Recipe recipe) {
			
			repository.update(recipe);
	}

		@Override
		public Recipe findById(Integer recipeId) {
		
			return repository.findById(recipeId);
		}

}
