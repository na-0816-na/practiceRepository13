package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Review;

public interface RegistReviewService {
	
	void add(Review review);
	
	  List<Review> getReviewsByRecipeId(Integer recipeId);
	

}
