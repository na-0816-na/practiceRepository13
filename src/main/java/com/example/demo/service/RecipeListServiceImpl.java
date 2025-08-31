package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RecipeListServiceImpl implements RecipeListService {
	
	private final RecipeRepository repository;

	@Override
	public List<Recipe> findByNameWildcard(String recipeName) {
		List<Recipe> list
			=repository.findByNameWildcard(recipeName);
		
		return list != null ? list : List.of();
			
		}	
	}
