package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Recipe;


public interface FavoriteService {

	    // お気に入り追加
	    void addFavorite(Integer userId, Integer recipeId);

	    // お気に入り削除
	    void removeFavorite(Integer userId, Integer recipeId);

	    // お気に入り一覧取得
	    List<Recipe> getFavoritesByUserId(Integer userId);

	    // お気に入り登録済みか確認
	    boolean isFavorite(Integer userId, Integer recipeId);
	}



