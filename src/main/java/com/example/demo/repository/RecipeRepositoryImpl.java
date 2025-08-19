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
public class RecipeRepositoryImpl implements RecipeRepository {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(Recipe recipe) {

		String sql =
			" INSERT INTO m_recipe " +
			" (user_id, recipe_name,  catch_phrase, category_id, how_to, post_date) " +
			" VALUES (?, ?, ?, ?, ?, ?) ";

		jdbcTemplate.update(sql, recipe.getUserId(),
								 recipe.getRecipeName(),
								 recipe.getCatchPhrase(),
								 recipe.getCategoryId(),
								 recipe.getHowTo(),
								 recipe.getPostDate());
								 
		
	}
	
	public Integer findCategoryIdByName(String categoryName) {
	    String sql = "SELECT category_id FROM m_category WHERE category_name = ?";
	    return jdbcTemplate.queryForObject(sql, Integer.class, categoryName);
	}

	
	
	@Override
	public List<Recipe> findByNameWildcard(String recipeName) {
		// TODO 自動生成されたメソッド・スタブ
	
		String sql = 
			    " SELECT " + 
			    "    mr.user_id, " + 
			    "    mr.recipe_name, " + 
			    "    mr.catch_phrase, " + 
			    "    mr.category_id, " + 
			    "    mc.category_name, " +
			    "    mr.how_to, " + 
			    "    mr.post_date " + 
			    " FROM m_category mc " +
			    " JOIN m_recipe mr ON mc.category_id = mr.category_id " +
			    " JOIN m_user mu ON mr.user_id = mu.user_id " +
			    " WHERE mr.recipe_name LIKE ? " + 
			    " ORDER BY mr.recipe_id DESC";

		
		String p = "%" + recipeName + "%";	// プレースホルダの値
		
		// SQLで検索（プレースホルダ：p）
		List<Map<String, Object>> list 
				= jdbcTemplate.queryForList(sql, p);
		
				// 値の取得⇒結果の格納
				List<Recipe> result = new ArrayList<Recipe>(); // 結果の初期化
				for (Map<String, Object> one : list) {
					Recipe recipe = new Recipe();
					recipe.setUserId((Integer)one.get("user_id"));
					recipe.setRecipeName((String)one.get("recipe_name"));
					recipe.setCatchPhrase((String)one.get("catch_phrase"));
					recipe.setCategoryId((Integer)one.get("category_id"));
					 recipe.setCategoryName((String) one.get("category_name")); 
					recipe.setHowTo((String)one.get("how_to"));
					
					 java.sql.Date sqlDate = (java.sql.Date) one.get("post_date");
					    recipe.setPostDate(sqlDate != null ? sqlDate.toLocalDate() : null);
					result.add(recipe);
				}

				return result;
			}
	
	
	

	@Override
	public void update(Recipe recipe) {
		
		String sql =
				" UPDATE             " + 
				"   m_recipe         " + 
				" SET                " + 
				"    user_id = ? ,       " + 
				"    recipe_name = ? ,       " + 
				"    catch_phrase = ? ,             " + 
				"    category_id = ? ,              " + 
				"    how_to = ? ,              " + 
				"    post_date = ? ,              " + 
				" WHERE              " + 
				"   recipe_id = ?    ";
		
		jdbcTemplate.update(sql, 
				recipe.getUserId(),
				recipe.getRecipeName(),
				recipe.getCatchPhrase(),
				recipe.getCategoryId(),
				recipe.getHowTo(),
				recipe.getPostDate(),
				recipe.getRecipeId()  );
		
	}

	@Override
	public void delete(Recipe recipe) {
		
		String sql =
				" DELETE              " + 
				" FROM                " + 
				"   m_recipe          " + 
				" WHERE               " + 
				"   recipe_id = ?     "; 
		
		jdbcTemplate.update(sql, recipe.getRecipeId());
		
	}
	@Override
	public Recipe findById(Integer recipeId) {
	    String sql =
	        " SELECT " +
	        "   mr.recipe_id, " +
	        "   mr.recipe_name, " +
	        "   mr.catch_phrase, " +
	        "   mr.how_to, " +
	        "   mr.post_date, " +
	        "   mc.category_name, " +
	        "   mu.user_name " +
	        " FROM m_recipe mr " +
	        " JOIN m_category mc ON mr.category_id = mc.category_id " +
	        " JOIN m_user mu ON mr.user_id = mu.user_id " +
	        " WHERE mr.recipe_id = ?";

	    Map<String, Object> row = jdbcTemplate.queryForMap(sql, recipeId);

	    Recipe recipe = new Recipe();
	    recipe.setRecipeId((Integer) row.get("recipe_id"));
	    recipe.setRecipeName((String) row.get("recipe_name"));
	    recipe.setCatchPhrase((String) row.get("catch_phrase"));
	    recipe.setHowTo((String) row.get("how_to"));
	    recipe.setCategoryName((String) row.get("category_name"));
	    recipe.setUserName((String) row.get("user_name"));

	    java.sql.Date sqlDate = (java.sql.Date) row.get("post_date");
	    recipe.setPostDate(sqlDate != null ? sqlDate.toLocalDate() : null);

	    return recipe;
	}

	
	}

		
	
