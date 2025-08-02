package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.RecipeEditForm;
import com.example.demo.service.EditService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EditController {

		private final EditService service;
	
	/*--- レシピ編集画面表示リクエスト ---*/
	@PostMapping("/show-edit-form")
	public String showEditForm(@ModelAttribute RecipeEditForm form) {
		return "regist-recipe";
	}


	/*--- レシピ更新リクエスト（登録画面より） ---*/
	@PostMapping("/edit-recipe")
	public String editRecipe(
			@Validated @ModelAttribute RecipeEditForm form,
			BindingResult result) {

		// 入力エラーがある場合には レシピ登録画面に戻す
		if (result.hasErrors()) {
			return "confirm-edit-recipe";
		}
		
		// 正常な場合に レシピ登録確認画面に 遷移する
		return "confirm-regist-recipe";
	}

	/*--- レシピ更新リクエスト（登録確認画面より） ---*/
	@PostMapping("/confirm-edit-recipe")
	public String confirmEditRecipe(
			@Validated RecipeEditForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		// 入力エラーがある場合には レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "edit-recipe";
		}

		/*Review r = new Review();
		r.setReviewId(form.getReviewId());
		r.setRestaurantId(form.getRestaurantId());
		r.setUserId(form.getUserId());
		r.setVisitDate(form.getVisitDate());
		r.setRating(form.getRating());
		r.setComment(form.getComment());
		
		service.edit(r);*/
		
		redirectAttributes.addFlashAttribute("msg", "(レシピを更新しました！)");
		redirectAttributes.addFlashAttribute("redirectPath", "/home");
		
		return "redirect:/complete";
	}

}
