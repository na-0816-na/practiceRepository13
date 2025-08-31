package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.Recipe;
import com.example.demo.form.RegistRecipeForm;
import com.example.demo.service.RecipeDetailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeDetailController {
	
	private final RecipeDetailService recipeDetailService;
	
	@GetMapping("/recipe-detail")
	public String recipeDetail(@ModelAttribute RegistRecipeForm form, Model model) {
	    // フォームのrecipeIdから検索
	    Integer recipeId = form.getRecipeId();

	    // DBから1件取得
	    Recipe recipe = recipeDetailService.findByRecipeId(recipeId);

	    // 取得したレシピをフォームに詰め直す
	    form.setRecipeName(recipe.getRecipeName());
	    form.setCatchPhrase(recipe.getCatchPhrase());
	    form.setCategoryId(recipe.getCategoryId());
	    form.setHowTo(recipe.getHowTo());
	    form.setUserId(recipe.getUserId());
	    form.setPostDate(recipe.getPostDate());
	    form.setDeliciousness(recipe.getDeliciousness());
	    form.setDifficulty(recipe.getDifficulty());
	    form.setQuickly(recipe.getQuickly());      
	    

	    model.addAttribute("recipe", recipe);
	    return "recipe-detail";  // 
	}

}
