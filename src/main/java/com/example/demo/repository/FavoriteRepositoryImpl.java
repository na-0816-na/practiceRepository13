package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Recipe;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FavoriteRepositoryImpl implements FavoriteRepository {
	
	private final JdbcTemplate jdbcTemplate;

	
	    @Override
	    public void addFavorite(Integer userId, Integer recipeId) {
	        String sql = "INSERT INTO t_favorite (user_id, recipe_id, reg_date) VALUES (?, ?, NOW())";
	        jdbcTemplate.update(sql, userId, recipeId);
	    }

	    @Override
	    public void removeFavorite(Integer userId, Integer recipeId) {
	        String sql = "DELETE FROM t_favorite WHERE user_id = ? AND recipe_id = ?";
	        jdbcTemplate.update(sql, userId, recipeId);
	    }

	    @Override
	    public boolean existsFavorite(Integer userId, Integer recipeId) {
	        String sql = "SELECT COUNT(*) FROM t_favorite WHERE user_id = ? AND recipe_id = ?";
	        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userId, recipeId);
	        return count != null && count > 0;
	    }

		@Override
		public List<Recipe> findFavoritesByUserId(Integer userId) {
		
		 String sql =
		            "SELECT r.recipe_id, r.recipe_name, r.catch_phrase, r.how_to, r.post_date, " +
		            "       c.category_name, u.user_name " +
		            "FROM t_favorite f " +
		            "JOIN m_recipe r ON f.recipe_id = r.recipe_id " +
		            "JOIN m_category c ON r.category_id = c.category_id " +
		            "JOIN m_user u ON r.user_id = u.user_id " +
		            "WHERE f.user_id = ? " +
		            "ORDER BY f.reg_date DESC";

		        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, userId);

		        List<Recipe> recipeList = new ArrayList<>();
		        for (Map<String, Object> one : list) {
		            Recipe recipe = new Recipe();
		            recipe.setRecipeId((Integer) one.get("recipe_id"));
		            recipe.setRecipeName((String) one.get("recipe_name"));
		            recipe.setCatchPhrase((String) one.get("catch_phrase"));
		            recipe.setHowTo((String) one.get("how_to"));
		            recipe.setCategoryName((String) one.get("category_name"));
		            recipe.setUserName((String) one.get("user_name"));

		            java.sql.Date sqlDate = (java.sql.Date) one.get("post_date");
		            recipe.setPostDate(sqlDate != null ? sqlDate.toLocalDate() : null);

		            recipeList.add(recipe);
		            
		            
		        }

		        return recipeList;
	}

}
