package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Recipe;

public interface FavoriteRepository {
	
	   // お気に入り追加
    void addFavorite(Integer userId, Integer recipeId);

    // お気に入り削除
    void removeFavorite(Integer userId, Integer recipeId);

    // お気に入りの全部取得（任意）
    List<Recipe> findFavoritesByUserId(Integer userId);

    // レシピをお気に入り登録しているか
    boolean existsFavorite(Integer userId, Integer recipeId);

}
