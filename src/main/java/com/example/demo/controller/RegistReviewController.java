package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Review;
import com.example.demo.form.RegistReviewForm;
import com.example.demo.service.RegistReviewService;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Controller
public class RegistReviewController {
	
	private final RegistReviewService reviewService;
	
	/*--- レビュー登録画面表示リクエスト ---*/
	@PostMapping("/show-review-form")
	public String showReviewForm(@ModelAttribute("registReviewForm") RegistReviewForm form) {
		return "regist-review";
	}

	/*--- レビュー登録画面表示リクエスト（確認画面からの戻り） ---*/
	@PostMapping("/show-review-form-ret")
	public String showReviewFormRet(@ModelAttribute("registReviewForm") RegistReviewForm form) {
		return "regist-review";
	}

	/*--- レビュー登録リクエスト（登録画面より） ---*/
	@PostMapping("/regist-review")
	public String registReview(
			@Validated @ModelAttribute("registReviewForm") RegistReviewForm form,
			BindingResult result) {

		// 入力エラーがある場合には レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "regist-review";
		}
		
		// 正常な場合に レビュー登録確認画面に 遷移する
		return "confirm-regist-review";
	}

	/*--- レビュー登録リクエスト（登録確認画面より） ---*/
	@PostMapping("/confirm-regist-review")
	public String confirmRegistReview(
			@Validated RegistReviewForm form,
			BindingResult result,
			RedirectAttributes redirectAttributes) {

		// 入力エラーがある場合には レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "regist-review";
			
		}
			
				 Review r = new Review();
			        r.setRecipeId(form.getRecipeId());
			        r.setUserId(form.getUserId()); // 
			        r.setPostDate(form.getPostDate());
			        r.setDeliciousness(form.getDeliciousness());
			        r.setDifficulty(form.getDifficulty());
			        r.setQuickly(form.getQuickly());

				
			    reviewService.add(r);
				
				redirectAttributes.addFlashAttribute("msg", "(レビュー登録)");
				
				return "redirect:/complete";
		

}


}
