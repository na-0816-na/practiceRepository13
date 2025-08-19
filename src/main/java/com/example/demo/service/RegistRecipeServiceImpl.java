package com.example.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RegistRecipeServiceImpl implements RegistRecipeService {
	
	private final RecipeRepository repository;
	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(Recipe recipe) {
		
		repository.add(recipe);

	}

	public Integer findCategoryIdByName(String categoryName) {
	    String sql = "SELECT category_id FROM m_category WHERE category_name = ?";
	    return jdbcTemplate.queryForObject(sql, Integer.class, categoryName);
	}
}
