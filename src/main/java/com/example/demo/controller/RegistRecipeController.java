package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Recipe;
import com.example.demo.form.RegistRecipeForm;
import com.example.demo.service.RegistRecipeService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userId")
public class RegistRecipeController {

	private final RegistRecipeService recipeService;
	private final UserService userService;

	
	/*---レシピ登録画面表示（入力フォーム） ---*/
	@GetMapping("/show-recipe-form")
	public String showRecipeForm(@ModelAttribute("registRecipeForm") RegistRecipeForm form) {
		return "regist-recipe";
	}
	

	/*---レシピ登録画面表示（登録確認画面から戻ったとき） ---*/
	@PostMapping("/show-recipe-form-ret")
	public String showRecipeFormRet(@ModelAttribute("registRecipeForm") RegistRecipeForm form) {
		return "regist-recipe";
	}

	/*--- レシピ登録リクエスト（登録画面より） ---*/
	@PostMapping("/regist-recipe")
	public String registRecipe(
			@Validated @ModelAttribute("registRecipeForm") RegistRecipeForm form,
			BindingResult result) {

		// 入力エラーがある場合には レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "regist-recipe";
		}
		
		switch(form.getCategoryId()) {
	    case 1: form.setCategoryName("包丁を使わない料理"); break;
	    case 2: form.setCategoryName("火を使わない料理"); break;
	    case 3: form.setCategoryName("冷凍保存可能な料理"); break;
	    case 4: form.setCategoryName("大量調理OKなもの"); break;
	}
		
		// 正常な場合に レシピ登録確認画面に 遷移する
		return "confirm-regist-recipe";
	}

	/*--- レシピ登録リクエスト（登録確認画面より） ---*/
	@PostMapping("/confirm-regist-recipe")
	public String confirmRegistRecipe(
			@Validated RegistRecipeForm form,
			BindingResult result,
			@SessionAttribute("userId") Integer userId,
			RedirectAttributes redirectAttributes) {

		// 入力エラーがある場合には レシピ登録画面に戻す
		if (result.hasErrors()) {
			return "regist-recipe";
		}
		
		String userName = userService.findUserNameById(userId);
		
		Recipe r = new Recipe();
		r.setRecipeName(form.getRecipeName());
		r.setCatchPhrase(form.getCatchPhrase());
		r.setHowTo(form.getHowTo());
		r.setCategoryId(form.getCategoryId());
		r.setPostDate(form.getPostDate());
		r.setUserId(userId);
		r.setUserName(userName); 
		r.setDeliciousness(form.getDeliciousness());
		r.setDifficulty(form.getDifficulty());
		r.setQuickly(form.getQuickly());
		
		recipeService.add(r);
		
		redirectAttributes.addFlashAttribute("msg", "(レシピを登録しました！)");
		redirectAttributes.addFlashAttribute("redirectPath", "/home");
		
		return "redirect:/complete";
	}

}
