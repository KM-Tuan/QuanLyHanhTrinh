/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.FoodCategory;
import com.kmt.service.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author kieum
 */
@Controller
public class FoodCategoryControlelr {

    @Autowired
    private FoodCategoryService cateSer;

    @GetMapping("/foodcategory/add")
    public String addCategoryForm(Model model) {
        model.addAttribute("foodCategory", new FoodCategory());
        model.addAttribute("categories", cateSer.getCate());
        return "addOrUpdateFoodCategory";  // tên file Thymeleaf của bạn
    }

    @GetMapping("/foodcategory/add/{id}")
    public String updateCategory(@PathVariable("id") int id, Model model) {

        FoodCategory foodcategory = cateSer.getCategoryById(id);
        model.addAttribute("foodCategory", foodcategory);
        model.addAttribute("categories", cateSer.getCate());
        return "addOrUpdateFoodCategory";
    }

    @PostMapping("/foodcategory/add/submit")
    public String addCategory(@ModelAttribute("foodCategory") FoodCategory newCategory) {
        cateSer.addOrUpdateCategory(newCategory);
        return "redirect:/foodcategory/add";
    }

    @GetMapping("/foodcategory/delete/{id}")
    public String deleteCategory(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        cateSer.deleteCategoryById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa danh mục thành công!");
        return "redirect:/foodcategory/add";
    }

}
