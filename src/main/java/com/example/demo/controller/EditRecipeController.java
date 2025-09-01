package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Recipe;
import com.example.demo.form.EditRecipeForm;
import com.example.demo.service.EditService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("userId")
public class EditRecipeController {

		private final EditService service;
		private final UserService userService;
	
	/*--- レシピ編集画面表示リクエスト ---*/
	@PostMapping("/show-edit-form")
	public String showEditForm(@ModelAttribute EditRecipeForm form, 
		                       @SessionAttribute("userId") Integer userId) {
		
		System.out.println("DEBUG: form = " + form);

		   
	form.setUserName(userService.findUserNameById(userId));

		return "edit-recipe";
	}


	/*--- レシピ更新リクエスト（登録画面より） ---*/
	@PostMapping("/edit-recipe")
	public String editRecipe(
			@Validated @ModelAttribute EditRecipeForm form,
			BindingResult result) {
		
		switch(form.getCategoryId()) {
	    case 1: form.setCategoryName("包丁を使わない料理"); break;
	    case 2: form.setCategoryName("火を使わない料理"); break;
	    case 3: form.setCategoryName("冷凍保存可能な料理"); break;
	    case 4: form.setCategoryName("大量調理OKなもの"); break;
		}

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
			@Validated EditRecipeForm form,
			BindingResult result,
			 @SessionAttribute("userId") Integer userId,
			RedirectAttributes redirectAttributes) {

		// 入力エラーがある場合には レビュー登録画面に戻す
		if (result.hasErrors()) {
			return "edit-recipe";
		}
		
String userName = userService.findUserNameById(userId);
		

	Recipe r = new Recipe();
	r.setRecipeId(form.getRecipeId()); 
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
		
		service.edit(r);
		
		redirectAttributes.addFlashAttribute("msg", "(レシピを更新しました！)");
		redirectAttributes.addFlashAttribute("redirectPath", "/home");
		
		return "redirect:/complete";
	}

}
