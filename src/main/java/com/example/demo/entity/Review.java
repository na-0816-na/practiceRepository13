package com.example.demo.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Review {
	
	private String userName;
	private Integer reviewId;
	private Integer recipeId;
	private Integer userId;
	private Date postDate;
	private Integer deliciousness;
	private Integer difficulty;
	private Integer quickly;

	
	

}
