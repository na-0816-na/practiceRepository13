package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Category;


public interface CategoryRepository {
	
	List<Category> findAll();


}
