package com.example.demo.form;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class RecipeRemoveForm {
	
	@Size(min=4, max=16, message="4文字から16文字で指定してください。")
	private String userName;
	
	@Size(min=1, max=64, message="1文字から64文字で指定してください。")
	private String recipeName;
	
	@Size(min=1, max=64, message="1文字から64文字で指定してください。")
	private String catchPhrase;
	
	@NotNull(message = "カテゴリーを選択してください。")
	private String category;

	@Size(min=1, max=10000, message="1文字から10000文字で指定してください。")
	private String howTo;

	
	@Past(message="今日の日付を入力してください。")
	private Date postDate;
	
	
}

