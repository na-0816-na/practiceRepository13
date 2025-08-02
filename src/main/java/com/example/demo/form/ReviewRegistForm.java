package com.example.demo.form;

import java.sql.Date;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.Data;
@Data
public class ReviewRegistForm {
	
		@NotNull(message="入力してください。")
		@Min(value=1,  message="正の整数を入力してください。")
		private Integer recipeId;
		
		private String recipeName;

		@Size(min=4, max=16, message="4文字から16文字で指定してください。")
		private String userName;

		@Past(message="今日の日付を入力してください。")
		private Date postDate;

		@NotNull(message="入力してください。")
		@Min(value=1, message="1-5で指定してください。")
		@Max(value=5, message="1-5で指定してください。")
		private Integer deliciousness ;
		private Integer difficulty ;
		private Integer quickley;
		
}
