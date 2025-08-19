package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Recipe;
import com.example.demo.form.RemoveRecipeForm;
import com.example.demo.service.RemoveService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userId")
public class RemoveRecipeController {

	private final RemoveService service;
	private final UserService userService;

	/*--- レシピ削除リクエスト（一覧画面より） ---*/
	@GetMapping("/remove-recipe")
	public String removeRecipe(
			@Validated @ModelAttribute RemoveRecipeForm form,
			BindingResult result) {

		// 項目内容にエラーがある場合には 例外を発生させる
		if (result.hasErrors()) {
			throw new IllegalArgumentException("**removeRecipe()**");
		}
		
		// 正常な場合に レシピ削除確認画面に 遷移する
		return "confirm-remove-recipe";
	}

	/*--- レシピ削除リクエスト（登録確認画面より） ---*/
	@GetMapping("/confirm-remove-recipe")
	public String confirmRemoveRecipe(
			@Validated RemoveRecipeForm form,
			BindingResult result,
			 @SessionAttribute("userId") Integer userId,
			RedirectAttributes redirectAttributes) {

		// 項目内容にエラーがある場合には 例外を発生させる
		if (result.hasErrors()) {
			throw new IllegalArgumentException("**removeRecipe()**");	
		}

String userName = userService.findUserNameById(userId);
		
		Recipe r = new Recipe();
		r.setRecipeName(form.getRecipeName());
		r.setCatchPhrase(form.getCatchPhrase());
		r.setHowTo(form.getHowTo());
		r.setPostDate(form.getPostDate());
		r.setUserId(userId);
		r.setUserName(userName); 
		
		service.remove(r);
		
		redirectAttributes.addFlashAttribute("msg", "(レシピを削除しました。)");
		redirectAttributes.addFlashAttribute("redirectPath", "/home");
		
		return "redirect:/complete";
	}

}