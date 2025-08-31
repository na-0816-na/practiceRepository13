package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Recipe;
import com.example.demo.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RemoveServiceImpl implements RemoveService {

	private final RecipeRepository repository;
	
	@Override
	public void remove(Recipe recipe) {

	// DBから対象のレシピを取得
	Recipe dbRecipe = repository.findById(recipe.getRecipeId());

	 //自分のレシピかどうかチェック
	if (dbRecipe != null && dbRecipe.getUserId().equals(recipe.getUserId())) {
	 repository.delete(dbRecipe); // 自分のレシピなら削除
		} else {
		  throw new IllegalStateException("他人のレシピは削除できません");
		        }
		    }

	@Override
	public Recipe findById(Integer recipeId) {
		return repository.findById(recipeId);
	}

}
