package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.FavoriteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class FavoriteServiceImpl implements FavoriteService {
	
	private final FavoriteRepository favoriteRepository;

    @Override
    public void addFavorite(Integer userId, Integer recipeId) {
        if (!favoriteRepository.existsFavorite(userId, recipeId)) {
            favoriteRepository.addFavorite(userId, recipeId);
        }
    }

    @Override
    public void removeFavorite(Integer userId, Integer recipeId) {
        favoriteRepository.removeFavorite(userId, recipeId);
    }

    @Override
    public List<Recipe> getFavoritesByUserId(Integer userId) {
        return favoriteRepository.findFavoritesByUserId(userId);
    }

    @Override
    public boolean isFavorite(Integer userId, Integer recipeId) {
        return favoriteRepository.existsFavorite(userId, recipeId);
    }
}
	


