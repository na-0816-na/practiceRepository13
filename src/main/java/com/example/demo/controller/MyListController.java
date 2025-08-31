package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.Recipe;
import com.example.demo.service.MyRecipeListService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MyListController {
	
   private final MyRecipeListService myRecipeListService;
   
   @GetMapping("/my-recipe")
   public String myRecipeList(@SessionAttribute("userId") Integer userId,
                              Model model) {
       List<Recipe> recipeList = myRecipeListService.getMyRecipe(userId);
       model.addAttribute("recipeList", recipeList);
       return "my-recipe-list"; 
   }
}