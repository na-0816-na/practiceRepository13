package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.form.LoginForm;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final UserService service;

	/*--- トップ ---*/
	@GetMapping("/top")
	public String top() {
		return "top";
	}

	/*--- ログイン ---*/
	@PostMapping("/login")
	public String LoginForm(@Validated @ModelAttribute("loginForm")  LoginForm form,
			BindingResult result) {
		
		
		// 入力エラーがある場合には レビュー登録画面に戻す
				if (result.hasErrors()) {
					return "login";
				}
				
				User user = service.findByEmail(form.getEmail());

				if (user == null || !user.getPassWord().equals(form.getPassWord())) {
				    result.reject("loginError", "メールアドレスまたはパスワードが違います");
				    return "login";
				}

		// 正常な場合には ホーム画面へ遷移する
				return "home";
			}
				
	
	/*--- 新規ユーザー登録 ---*/
	@PostMapping("/registration")
	public String RegistrationForm(@Validated @ModelAttribute("loginForm")  LoginForm form,
			BindingResult result,RedirectAttributes redirectAttributes)  {
		
		// 入力エラーがある場合には レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "new-regist";
			
		}
		
redirectAttributes.addFlashAttribute("msg", "(ユーザー登録)");
redirectAttributes.addFlashAttribute("redirectPath", "/top");
		
		return "redirect:/complete";
		
	}
	
	/*--- ホーム ---*/
	@PostMapping("/home")
	public String Home(@ModelAttribute("loginForm")  LoginForm form) {
		return "home";
	}
	
	/*--- ログアウト確認画面 ---*/
	@PostMapping("/logout")
	public String Logout() {
		return "logout";
		
	}
}