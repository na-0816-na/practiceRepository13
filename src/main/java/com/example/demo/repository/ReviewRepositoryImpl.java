package com.example.demo.repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Review;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

	private final JdbcTemplate jdbcTemplate;
	

	@Override
	public void add(Review review) {

		String sql =
				" INSERT INTO t_review " +
				" (recipe_id, user_id, post_date, deliciousness, difficulty, quickly) " +
				" VALUES (?, ?, ?, ?, ?) ";

			jdbcTemplate.update(sql, review.getRecipeId(),
									 review.getUserId(),
									 review.getPostDate(),
									 review.getDeliciousness(),
									 review.getDifficulty(),
									 review.getQuickly());
			
	
	}
@Override	
public List<Review> selectByRecipeId(Integer recipeId) {

	String sql = 
			"  SELECT                 " + 
			"    user_name,       " + 
			"    post_date,          " + 
			"    deliciousness,              " + 
			"    difficulty,             " + 
			"    quickly             " + 
			"  FROM                   " + 
			"    t_review             " + 
			"  JOIN m_user mu                         " +
			"  	   ON tr.user_id = mu.user_id;  " +
			"  WHERE                  " + 
			"    recipe_id = ?    " + 
			"  ORDER BY               " + 
			"    post_date DESC,     " + 
			"    review_id ASC        ";
		
		// SQLで検索（プレースホルダ：p）
		List<Map<String, Object>> list 
				= jdbcTemplate.queryForList(sql, recipeId);
		
		// 値の取得⇒結果の格納
		List<Review> result = new ArrayList<Review>(); // 結果の初期化
		for (Map<String, Object> one : list) {
			Review review = new Review();
			review.setUserName((String)one.get("user_name"));
			review.setPostDate((Date)one.get("post_date"));
			review.setDeliciousness((Integer)one.get("deliciousness"));
			review.setDifficulty((Integer)one.get("difficulty"));
			review.setQuickly((Integer)one.get("quickly"));
			result.add(review);
		}
		
		return result;
	}
	}