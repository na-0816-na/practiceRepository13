package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReviewListServiceImpl implements ReviewListService {

	private final ReviewRepository repository;
	@Override
	public List<Review> selectByRecipeId(Integer recipeId) {
		
		List<Review> list = repository.selectByRecipeId(recipeId);
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
