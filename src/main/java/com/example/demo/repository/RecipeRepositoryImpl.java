package com.example.demo.repository;

import java.sql.Date;
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
			" (user_id, recipe_name,  catch_phrase, category_id, how_to, post_date,) " +
			" VALUES (?, ?, ?, ?, ?) ";

		jdbcTemplate.update(sql, recipe.getUserId(),
								 recipe.getRecipeName(),
								 recipe.getCatchPhrase(),
								 recipe.getCategoryId(),
								 recipe.getHowTo(),
								 recipe.getPostDate());
								 
		
	}

	
	
	@Override
	public List<Recipe> findByNameWildcard(String recipeName) {
		// TODO 自動生成されたメソッド・スタブ
	
		
		String sql = 
				"  SELECT                 " + 
				"    user_id ,       " + 
				"    recipe_name ,       " + 
				"    catch_phrase ,             " + 
				"    category_id ,              " + 
				"    how_to ,              " + 
				"    post_date ,              " + 
				"  FROM                   " + 
				"  m_category mc                                      " +
				"  JOIN m_ mr ON mc.category_id = mr.category_id                          " +
				"  JOIN m_user mu ON mr.user_id = mu.user_id " +
				"  WHERE                  " + 
				"    recipe_name LIKE ?    " + 
				"  ORDER BY               " + 
				"    recipe_id DESC    " ;
		
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
					recipe.setHowTo((String)one.get("how_to"));
					recipe.setPostDate((Date)one.get("post_date"));
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

	
	}

		
	
