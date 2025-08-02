package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.RecipeRegistForm;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RegistRecipeController {

	private final UserService service;

	
	/*---レシピ登録画面表示リクエスト ---*/
	@PostMapping("/show-recipe-form")
	public String showRecipeForm(@ModelAttribute RecipeRegistForm form) {
		return "regist-recipe";
	}

	/*---レシピ登録画面表示リクエスト（確認画面からの戻り） ---*/
	@PostMapping("/show-recipe-form-ret")
	public String showRecipeFormRet(@ModelAttribute RecipeRegistForm form) {
		return "regist-recipe";
	}

	/*--- レシピ登録リクエスト（登録画面より） ---*/
	@PostMapping("/regist-recipe")
	public String registRecipe(
			@Validated @ModelAttribute RecipeRegistForm form,
			BindingResult result) {

		// 入力エラーがある場合には レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "regist-recipe";
		}
		
		// 正常な場合に レシピ登録確認画面に 遷移する
		return "confirm-regist-recipe";
	}

	/*--- レシピ登録リクエスト（登録確認画面より） ---*/
	@PostMapping("/confirm-regist-recipe")
	public String confirmRegistRecipe(
			@Validated RecipeRegistForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		// 入力エラーがある場合には レシピ登録画面に戻す
		if (result.hasErrors()) {
			return "regist-recipe";
		}

		/*Review r = new Review();
		r.setRestaurantId(form.getRestaurantId());
		r.setUserId(form.getUserId());
		r.setVisitDate(form.getVisitDate());
		r.setRating(form.getRating());
		r.setComment(form.getComment());
		service.regist(r);*/
		
		redirectAttributes.addFlashAttribute("msg", "(レシピを登録しました！)");
		redirectAttributes.addFlashAttribute("redirectPath", "/home");
		
		return "redirect:/complete";
	}

}
