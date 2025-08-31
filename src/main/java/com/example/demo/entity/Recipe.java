package com.example.demo.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
	
	private Integer recipeId;
	private String recipeName;
	private Integer userId;
	private String catchPhrase;
	private String howTo;
	private Integer categoryId;
	private String categoryName;
	private LocalDate postDate;
	private String userName;
	private Integer deliciousness;
	private Integer difficulty;
	private Integer quickly;

 
}
	
