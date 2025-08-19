package com.example.demo.form;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class RemoveRecipeForm {
	
	@Size(min=4, max=16, message="4文字から16文字で指定してください。")
	private String userName;
	
	@Size(min=1, max=64, message="1文字から64文字で指定してください。")
	private String recipeName;
	
	@Size(min=1, max=64, message="1文字から64文字で指定してください。")
	private String catchPhrase;
	
	private String categoryName;

	@Size(min=1, max=10000, message="1文字から10000文字で指定してください。")
	private String howTo;

	
	@Past(message="過去の日付を入力してください。")
	@NotNull(message="日付を入力してください。")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate postDate;
	
	private Integer category;
	
	
}

