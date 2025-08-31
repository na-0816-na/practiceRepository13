package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.entity.Recipe;
import com.example.demo.form.FavoriteForm;
import com.example.demo.service.FavoriteService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor

public class FavoriteController {
	
	private final FavoriteService favoriteService;

	//お気に入り追加 
    @PostMapping("/favorite/add")
    public String addFavorite(@SessionAttribute("userId") Integer userId,
                              @ModelAttribute FavoriteForm form) {
        favoriteService.addFavorite(userId, form.getRecipeId());
        return "redirect:/recipe-detail?recipeId=" + form.getRecipeId();
    }
    
    //お気に入り削除 
    @PostMapping("/favorite/remove")
    public String removeFavorite(@ModelAttribute FavoriteForm form) {
        favoriteService.removeFavorite(form.getUserId(), form.getRecipeId());
        return "redirect:/recipe-detail?recipeId=" + form.getRecipeId();
    }

    // お気に入り一覧 
@GetMapping("/favorite")
public String favoriteList(@SessionAttribute("userId") Integer userId, Model model) {
    List<Recipe> recipeList = favoriteService.getFavoritesByUserId(userId);
    model.addAttribute("recipeList", recipeList);
    return "favorite-list";  
}
}

   