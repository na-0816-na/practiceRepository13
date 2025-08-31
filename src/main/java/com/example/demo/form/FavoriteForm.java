package com.example.demo.form;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FavoriteForm {
	
	private Integer recipeId;
	
	private Integer favoriteId;
	
	private Integer userId;
	
	private LocalDate regDate;

}
