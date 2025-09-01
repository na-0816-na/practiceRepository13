package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.Recipe;
import com.example.demo.form.RegistRecipeForm;
import com.example.demo.service.FavoriteService;
import com.example.demo.service.RecipeDetailService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeDetailController {
	
    private final RecipeDetailService recipeDetailService;
    private final FavoriteService favoriteService;  

    @GetMapping("/recipe-detail")
    public String recipeDetail(@ModelAttribute RegistRecipeForm form,
                               @SessionAttribute("userId") Integer userId,
                               Model model) {
        Integer recipeId = form.getRecipeId();

        // レシピを取得
        Recipe recipe = recipeDetailService.findByRecipeId(recipeId);

        // お気に入り状態を取得
        boolean isFavorite = favoriteService.isFavorite(userId, recipeId);

        // モデルに渡す
        model.addAttribute("recipe", recipe);
        model.addAttribute("isFavorite", isFavorite);

        return "recipe-detail";
    }
}
