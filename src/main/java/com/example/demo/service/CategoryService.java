package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Recipe;

public interface CategoryService {
	
	public List<Recipe> findByCategoryId(Integer categoryId);

	public String findNameById(Integer categoryId);

	
}
	   