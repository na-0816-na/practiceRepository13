package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryRepository categoryRepository;
	
	public List<Recipe> findByCategoryId(Integer categoryId) {
	    return categoryRepository.findByCategoryId(categoryId);
	}

	@Override
	public String findNameById(Integer categoryId) {
	    return categoryRepository.findNameById(categoryId);
	}}

