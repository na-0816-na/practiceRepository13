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
			" (user_id, recipe_name,  catch_phrase, category_id, how_to, post_date, deliciousness, difficulty, quickly) " +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

		jdbcTemplate.update(sql, recipe.getUserId(),
								 recipe.getRecipeName(),
								 recipe.getCatchPhrase(),
								 recipe.getCategoryId(),
								 recipe.getHowTo(),
								 recipe.getPostDate(),
								 recipe.getDeliciousness(),
								 recipe.getDifficulty(),
								 recipe.getQuickly());
		
								 
		
	}
	
	public Integer findCategoryIdByName(String categoryName) {
	    String sql = "SELECT category_id FROM m_category WHERE category_name = ?";
	    return jdbcTemplate.queryForObject(sql, Integer.class, categoryName);
	}

	
	
	@Override
	public List<Recipe> findByNameWildcard(String recipeName) {
	
		String sql = 
			    " SELECT " + 
			    "    mr.recipe_id, " +       
			    "    mr.user_id, " + 
			    "    mr.recipe_name, " + 
			    "    mr.catch_phrase, " + 
			    "    mr.category_id, " + 
			    "    mc.category_name, " +
			    "    mr.how_to, " + 
			    "    mr.deliciousness, " + 
			    "    mr.difficulty, " + 
			    "    mr.quickly, " + 
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
					 recipe.setRecipeId((Integer)one.get("recipe_id"));
					recipe.setUserId((Integer)one.get("user_id"));
					recipe.setRecipeName((String)one.get("recipe_name"));
					recipe.setCatchPhrase((String)one.get("catch_phrase"));
					recipe.setCategoryId((Integer)one.get("category_id"));
					recipe.setCategoryName((String) one.get("category_name")); 
					recipe.setHowTo((String)one.get("how_to"));
					recipe.setDeliciousness((Integer)one.get("deliciousness"));
					recipe.setDifficulty((Integer)one.get("difficulty"));
					recipe.setQuickly((Integer)one.get("quickly"));
					
					 java.sql.Date sqlDate = (java.sql.Date) one.get("post_date");
					    recipe.setPostDate(sqlDate != null ? sqlDate.toLocalDate() : null);
					result.add(recipe);
				}

				return result;
			}
	
	
	

	@Override
	public void update(Recipe recipe) {
		System.out.println("UPDATE 実行: id=" + recipe.getRecipeId());

		
		
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
				"    deliciousness = ? ,              " + 
				"    difficulty = ? ,              " + 
				"    quickly = ?               " + 
				" WHERE              " + 
				"   recipe_id = ?    ";
		
		jdbcTemplate.update(sql, 
				recipe.getUserId(),
				recipe.getRecipeName(),
				recipe.getCatchPhrase(),
				recipe.getCategoryId(),
				recipe.getHowTo(),
				recipe.getPostDate(),
				recipe.getDeliciousness(),
				recipe.getDifficulty(),
				recipe.getQuickly(),
				recipe.getRecipeId());
				
		
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
			    " SELECT mr.user_id, mr.recipe_id, mr.recipe_name, mr.catch_phrase, mr.how_to, mr.post_date, " +
			    "        mr.category_id, mc.category_name, mu.user_name, " +
			    "        mr.deliciousness, mr.difficulty, mr.quickly " +
			    " FROM m_recipe mr " +
			    " JOIN m_category mc ON mr.category_id = mc.category_id " +
			    " JOIN m_user mu ON mr.user_id = mu.user_id " +
			    " WHERE mr.recipe_id = ?";

	    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, recipeId);

	    if (list.isEmpty()) {
	        return null; // 0件なら null 返す
	    }

	    Map<String, Object> one = list.get(0);

	    Recipe recipe = new Recipe();
	    recipe.setUserId((Integer) one.get("user_id"));
	    recipe.setRecipeId((Integer) one.get("recipe_id"));
	    recipe.setRecipeName((String) one.get("recipe_name"));
	    recipe.setCatchPhrase((String) one.get("catch_phrase"));
	    recipe.setHowTo((String) one.get("how_to"));
	    recipe.setCategoryId((Integer) one.get("category_id"));
	    recipe.setCategoryName((String) one.get("category_name"));
	    recipe.setUserName((String) one.get("user_name"));
	    recipe.setDeliciousness((Integer) one.get("deliciousness"));
	    recipe.setDifficulty((Integer) one.get("difficulty"));
	    recipe.setQuickly((Integer) one.get("quickly"));

	    java.sql.Date sqlDate = (java.sql.Date) one.get("post_date");
	    recipe.setPostDate(sqlDate != null ? sqlDate.toLocalDate() : null);

	    return recipe;
	}


	@Override
	public List<Recipe> findByUserId(Integer userId) {
		 String sql =
			        "SELECT r.recipe_id, r.recipe_name, r.catch_phrase, c.category_name " +
			        "FROM m_recipe r " +
			        "JOIN m_category c ON r.category_id = c.category_id " +
			        "WHERE r.user_id = ? " +
			        "ORDER BY r.post_date DESC";

			    List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, userId);

			    List<Recipe> result = new ArrayList<>();
			    for (Map<String, Object> one : list) {
			        Recipe recipe = new Recipe();
			        recipe.setRecipeId((Integer) one.get("recipe_id"));
			        recipe.setRecipeName((String) one.get("recipe_name"));
			        recipe.setCatchPhrase((String) one.get("catch_phrase"));
			        recipe.setCategoryName((String) one.get("category_name"));
			        result.add(recipe);
			    }
			    return result;
			}
}

	
