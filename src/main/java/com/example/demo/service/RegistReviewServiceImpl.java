package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RegistReviewServiceImpl implements RegistReviewService {
	
	private final ReviewRepository repository;

	@Override
	public void add(Review review) {
		
		repository.add(review);

	}
	
	 public List<Review> getReviewsByRecipeId(Integer recipeId) {
	        return repository.selectByRecipeId(recipeId);

}
}