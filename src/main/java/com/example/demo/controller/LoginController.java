package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.User;
import com.example.demo.form.LoginForm;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userId")
public class LoginController {

	private final UserService service;

	/*--- トップ ---*/
	@GetMapping("/top")
	public String top() {
		return "top";
	}

	/*--- ログイン ---*/
	@GetMapping("/login")
	public String LoginForm(@Validated @ModelAttribute("loginForm")  LoginForm form,
			BindingResult result,
			 Model model) {
		
		
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
				model.addAttribute("userId", user.getUserId());
				 return "redirect:/home";
			}
	
	
				
	/*--- 新規ユーザー登録 ---*/
	@PostMapping("/registration")
	public String RegistrationForm(@Validated @ModelAttribute("loginForm")  LoginForm form,
			BindingResult result,RedirectAttributes redirectAttributes,
			 Model model)  {
		
		
		// 入力エラーがある場合には レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "new-regist";
			
		}
		
		User r = new User();
		r.setEmail(form.getEmail());
		r.setPassWord(form.getPassWord());
		r.setUserName(form.getUserName()); 
		
		try {
          
		
		service.registerUser(r);
		
		
		
redirectAttributes.addFlashAttribute("msg", "(ユーザー登録)");
redirectAttributes.addFlashAttribute("redirectPath", "/top");
		
		return "redirect:/complete";
		
		} catch (IllegalArgumentException e) {
	        // 重複エラー時の処理
	        result.rejectValue("email", "duplicate", "このメールアドレスは既に登録されています");
	        return "new-regist";
	    }
		}
	
	//ホーム画面へ
		@GetMapping("/home")
		public String home(@SessionAttribute("userId") Integer userId, Model model) {
		    String userName = service.findUserNameById(userId);
		    model.addAttribute("userName", userName);
		    return "home";
		}
		

	
	//ログアウト確認画面
		@GetMapping("/logout")
		public String logout() {
		    return "logout";
		}

		//ログアウト処理
		@PostMapping("/logout/excute")
		public String doLogout(SessionStatus sessionStatus,
		                       RedirectAttributes redirectAttributes) {
			
		    sessionStatus.setComplete();

		    redirectAttributes.addFlashAttribute("msg", "(ログアウト)");
		    redirectAttributes.addFlashAttribute("redirectPath", "/top");

		    // 完了画面へ
		    return "redirect:/complete";
}
}