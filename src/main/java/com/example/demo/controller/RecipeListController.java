package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Recipe;
import com.example.demo.form.RecipeSearchForm;
import com.example.demo.service.RecipeListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeListController {
	
	private final RecipeListService service;

	//検索画面
	@GetMapping("/recipe-search")
	private String recipeSearch(
			@ModelAttribute RecipeSearchForm form) {
		return "recipe-list";
	}
	
	
	//検索結果表示
	@PostMapping("/search-results")
	private String recipeList(
			@ModelAttribute RecipeSearchForm form,
			Model model){
				
		
		List<Recipe> list 
					=service.findByNameWildcard(form.getRecipeName());
				
		
		
		model.addAttribute("recipeList",list);
		
		return "recipe-list";
			
			}
	
	//カテゴリ別検索結果表示
	@PostMapping("/category-search")
	private String recipeSearch(
			@ModelAttribute RecipeSearchForm form,
			Model model){
		return "category-list";
}
}