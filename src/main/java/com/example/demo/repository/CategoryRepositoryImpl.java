package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Recipe;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {
        String sql = "SELECT category_id, category_name FROM m_category";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
        List<Category> categoryList = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            Category category = new Category();
            category.setCategoryId((Integer) row.get("category_id"));
            category.setCategoryName((String) row.get("category_name"));
            categoryList.add(category);
        }

        return categoryList;

    }

    
    @Override
    public List<Recipe> findByCategoryId(Integer categoryId) {
        String sql = "SELECT r.recipe_id, r.recipe_name, r.catch_phrase, c.category_name " +
                     "FROM m_recipe r " +
                     "JOIN m_category c ON r.category_id = c.category_id " +
                     "WHERE r.category_id = ?";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, categoryId);

        List<Recipe> recipeList = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Recipe recipe = new Recipe();
            recipe.setRecipeId((Integer) row.get("recipe_id"));
            recipe.setRecipeName((String) row.get("recipe_name"));
            recipe.setCatchPhrase((String) row.get("catch_phrase"));
            recipe.setCategoryName((String) row.get("category_name"));
            recipeList.add(recipe);
        }
        return recipeList;
    }
    @Override
    public String findNameById(Integer categoryId) {
        String sql = "SELECT category_name FROM m_category WHERE category_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, categoryId);
    }
}
    

