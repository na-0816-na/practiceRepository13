package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Recipe;
import com.example.demo.form.RecipeSearchForm;
import com.example.demo.service.CategoryService;
import com.example.demo.service.RecipeListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeListController {
	
	private final RecipeListService service;
	private final CategoryService categoryService;

	//検索画面
	@GetMapping("/recipe-search")
	private String recipeSearch(
			@ModelAttribute RecipeSearchForm form) {
		return "recipe-list";
	}
	
	
	//検索結果表示
	@PostMapping("/recipe-search")
	private String recipeList(
			@ModelAttribute RecipeSearchForm form,
			Model model){
				
		
		List<Recipe> list 
					=service.findByNameWildcard(form.getRecipeName());
				
		
		
		model.addAttribute("recipeList",list);
		
		return "recipe-list";
			
			}
	
	//カテゴリ別検索結果表示
	@GetMapping("/category-search")
	private String recipeSearch(
			@ModelAttribute("categoryId") RecipeSearchForm form,
			Model model){
	
		Integer categoryId = form.getCategoryId();

        // カテゴリのレシピ一覧を取得する
        List<Recipe> recipes = categoryService.findByCategoryId(categoryId);
        model.addAttribute("recipeList", recipes);

        String categoryName = categoryService.findNameById(categoryId);
        model.addAttribute("categoryName", categoryName);

        return "category-list";
    }
}