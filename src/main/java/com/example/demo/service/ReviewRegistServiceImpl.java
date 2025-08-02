package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Review;
import com.example.demo.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ReviewRegistServiceImpl implements ReviewRegistService {
	
	private final ReviewRepository repository;

	@Override
	public void add(Review review) {
		
		repository.add(review);

	}

}
