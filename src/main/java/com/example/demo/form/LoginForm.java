package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginForm {
	
	@Size(min=4, max=16, message="4文字から16文字で指定してください。")
	@Pattern(regexp = ".*\\S.*", message = "空白のみは入力できません。")
	private String userName;
	
	@Size(min=6, max=16, message="6文字から20文字で指定してください。")
	@Pattern(regexp = ".*\\S.*", message = "空白のみは入力できません。")
	private String passWord;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "正しい形式のメールアドレスを入力してください")
	private String email;
}

