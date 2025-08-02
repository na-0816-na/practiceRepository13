package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginForm {
	
	@Size(min=4, max=16, message="4文字から16文字で指定してください。")
	private String userName;
	
	@Size(min=6, max=16, message="6文字から20文字で指定してください。")
	private String passWord;
	
	@NotNull(message="入力してください。")
	
	private String email;
	
	
		
	

}
