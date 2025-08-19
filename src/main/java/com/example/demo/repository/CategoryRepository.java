package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Category;
import com.example.demo.entity.Recipe;


public interface CategoryRepository {
	
	List<Category> findAll();
	
	 List<Recipe> findByCategoryId(Integer categoryId);
	 
	 String findNameById(Integer categoryId);


}
