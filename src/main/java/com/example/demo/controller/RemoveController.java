package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.form.RecipeRemoveForm;
import com.example.demo.service.RemoveService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RemoveController {

	private final RemoveService service;

	/*--- レシピ削除リクエスト（一覧画面より） ---*/
	@PostMapping("/remove-recipe")
	public String removeRecipe(
			@Validated @ModelAttribute RecipeRemoveForm form,
			BindingResult result) {

		// 項目内容にエラーがある場合には 例外を発生させる
		if (result.hasErrors()) {
			throw new IllegalArgumentException("**removeRecipe()**");
		}
		
		// 正常な場合に レシピ削除確認画面に 遷移する
		return "confirm-remove-recipe";
	}

	/*--- レシピ削除リクエスト（登録確認画面より） ---*/
	@PostMapping("/confirm-remove-recipe")
	public String confirmRemoveRecipe(
			@Validated RecipeRemoveForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		// 項目内容にエラーがある場合には 例外を発生させる
		if (result.hasErrors()) {
			throw new IllegalArgumentException("**removeRecipe()**");	
		}

		/*Review r = new Review();
		r.setReviewId(form.getReviewId());
		r.setRestaurantId(form.getRestaurantId());
		r.setUserId(form.getUserId());
		r.setVisitDate(form.getVisitDate());
		r.setRating(form.getRating());
		r.setComment(form.getComment());
		
		service.remove(r);*/
		
		redirectAttributes.addFlashAttribute("msg", "(レシピを削除しました。)");
		redirectAttributes.addFlashAttribute("redirectPath", "/home");
		
		return "redirect:/complete";
	}

}