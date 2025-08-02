package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Review;
import com.example.demo.form.ReviewSearchForm;
import com.example.demo.service.ReviewListService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class ReviewListController {
	private final ReviewListService service;

//検索リクエスト	
	@PostMapping("/search-review")
	public String searchReview(
			@ModelAttribute ReviewSearchForm form,
			Model model) {
		

		List<Review> list = service.selectByRecipeId(form.getRecipeId());
		
			model.addAttribute("reviewList", list != null ? list : List.of());
		

		return "review-list";
	}
}
