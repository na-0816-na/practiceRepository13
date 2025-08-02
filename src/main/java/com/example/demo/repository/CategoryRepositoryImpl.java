package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Category;

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
}



