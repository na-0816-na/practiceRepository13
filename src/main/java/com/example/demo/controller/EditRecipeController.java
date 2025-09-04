package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /** 編集画面表示 */
    @PostMapping("/show-edit-form")
    public String showEditForm(@RequestParam("recipeId") Integer recipeId,
                               @SessionAttribute("userId") Integer userId,
                               Model model) {

        // DBから取得
        Recipe recipe = service.findById(recipeId);
        if (recipe == null) {
            // 存在しない場合はホームにリダイレクト
            return "redirect:/home";
        }

        // フォームに詰め替え
        EditRecipeForm f = new EditRecipeForm();
        f.setRecipeId(recipe.getRecipeId());
        f.setRecipeName(recipe.getRecipeName());
        f.setCatchPhrase(recipe.getCatchPhrase());
        f.setCategoryId(recipe.getCategoryId());
        f.setCategoryName(recipe.getCategoryName());
        f.setHowTo(recipe.getHowTo());
        f.setPostDate(recipe.getPostDate());
        f.setDeliciousness(recipe.getDeliciousness());
        f.setDifficulty(recipe.getDifficulty());
        f.setQuickly(recipe.getQuickly());
        f.setUserName(userService.findUserNameById(userId));

        model.addAttribute("editRecipeForm", f);
        return "edit-recipe";
    }

    /** 確認画面へ */
    @PostMapping("/edit-recipe")
    public String editRecipe(@Validated @ModelAttribute("editRecipeForm") EditRecipeForm form,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "edit-recipe";
        }

        switch (form.getCategoryId()) {
            case 1: form.setCategoryName("包丁を使わない料理"); break;
            case 2: form.setCategoryName("火を使わない料理"); break;
            case 3: form.setCategoryName("冷凍保存可能な料理"); break;
            case 4: form.setCategoryName("大量調理OKなもの"); break;
        }

        return "confirm-edit-recipe";
    }

    /** 更新実行 */
    @PostMapping("/confirm-edit-recipe")
    public String confirmEditRecipe(@Validated @ModelAttribute("editRecipeForm") EditRecipeForm form,
                                    BindingResult result,
                                    @SessionAttribute("userId") Integer userId,
                                    RedirectAttributes redirectAttributes) {

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

        // ここで必ず更新処理を呼ぶ
        service.edit(r);

        redirectAttributes.addFlashAttribute("msg", "(レシピを更新しました！)");
        redirectAttributes.addFlashAttribute("redirectPath", "/home");

        return "redirect:/complete";
    }
}