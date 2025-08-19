package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
public class RegistReviewForm {
	
		
		private Integer recipeId;
		
		private Integer userId;

		private String recipeName;

		@Size(min=4, max=16, message="4文字から16文字で指定してください。")
		private String userName;

		@Past(message="過去の日付を入力してください。")
		@NotNull(message="日付を入力してください。")
		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate postDate;
		

		@NotNull(message="入力してください。")
		@Min(value=1, message="1-5で指定してください。")
		@Max(value=5, message="1-5で指定してください。")
		private Integer deliciousness ;
		
		@NotNull(message="入力してください。")
		@Min(value=1, message="1-5で指定してください。")
		@Max(value=5, message="1-5で指定してください。")
		private Integer difficulty ;
		
		@NotNull(message="入力してください。")
		@Min(value=1, message="1-5で指定してください。")
		@Max(value=5, message="1-5で指定してください。")
		private Integer quickly;
		
}
