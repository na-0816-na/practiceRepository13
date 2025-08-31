package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	@PostMapping("/remove-recipe")
	public String removeRecipe(
	        @ModelAttribute RemoveRecipeForm form,
	        Model model) {

	    // recipeId が null ならエラー
	    if (form.getRecipeId() == null) {
	        throw new IllegalArgumentException("recipeId が渡されませんでした");
	    }

	    // DBからレシピ情報を取得
	    Recipe recipe = service.findById(form.getRecipeId());

	    // Formに詰め直す（確認画面で表示する用）
	    form.setUserName(recipe.getUserName());
	    form.setRecipeName(recipe.getRecipeName());
	    form.setCatchPhrase(recipe.getCatchPhrase());
	    form.setHowTo(recipe.getHowTo());
	    form.setCategoryId(recipe.getCategoryId());
	    form.setCategoryName(recipe.getCategoryName());
	    form.setPostDate(recipe.getPostDate());
	    form.setDeliciousness(recipe.getDeliciousness()); 
	    form.setDifficulty(recipe.getDifficulty());       
	    form.setQuickly(recipe.getQuickly());            

	    model.addAttribute("removeRecipeForm", form);

	    // 確認画面に遷移
	    return "confirm-remove-recipe";
	}

	/*--- レシピ削除リクエスト（登録確認画面より） ---*/
	@PostMapping("/confirm-remove-recipe")
	public String confirmRemoveRecipe(
	        RemoveRecipeForm form,
	        @SessionAttribute("userId") Integer userId,
	        RedirectAttributes redirectAttributes) {

	    // DBから対象レシピを取得
	    Recipe recipe = service.findById(form.getRecipeId());

	    // 自分のレシピかチェックして削除
	    if (recipe != null && recipe.getUserId().equals(userId)) {
	        service.remove(recipe);
	        redirectAttributes.addFlashAttribute("msg", "レシピを削除しました。");
	        redirectAttributes.addFlashAttribute("redirectPath", "/home");
	        return "redirect:/complete";
	    } else {
	        throw new IllegalStateException("他人のレシピは削除できません");
	    }
	}
}