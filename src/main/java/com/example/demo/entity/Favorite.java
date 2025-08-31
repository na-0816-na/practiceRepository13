package com.example.demo.entity;

import java.time.LocalDate;

import lombok.Data;
@Data
public class Favorite {
		
		private Integer favoriteId;
		private Integer recipeId;
		private Integer userId;
		private LocalDate regDate;
}
