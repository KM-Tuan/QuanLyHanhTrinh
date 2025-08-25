/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Food;
import com.kmt.pojo.FoodCategory;
import com.kmt.service.FoodCategoryService;
import com.kmt.service.FoodService;
import java.util.List;
import java.util.stream.Collectors;
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
public class FoodController {

    @Autowired
    private FoodService foodSer;

    @Autowired
    private FoodCategoryService cateSer;

    @GetMapping("/foods")
    public String listFoods(Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        List<Food> foods = foodSer.getFoodsPaginated(page, size);
        long totalFoods = foodSer.countFoods();
        int totalPages = (int) Math.ceil((double) totalFoods / size);

        model.addAttribute("foods", foods);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);

        return "foods";
    }

    @GetMapping("/addChoice")
    public String showAddChoicePage() {
        return "addChoice"; // tên file .html trong templates
    }

    @GetMapping("/foods/add")
    public String addFood(Model model) {
        model.addAttribute("food", new Food());
        model.addAttribute("categories", cateSer.getCate());
        return "addOrUpdateFood";
    }

    @GetMapping("/foods/add/{id}")
    public String updateFood(@PathVariable("id") int id, Model model) {
        Food food = foodSer.getFoodById(id);
        List<FoodCategory> categories = cateSer.getCate();

        model.addAttribute("food", food);
        model.addAttribute("categories", categories);

        return "addOrUpdateFood";
    }

    @PostMapping("foods/add/submit")
    public String submitFood(@ModelAttribute Food food) {
        foodSer.addOrUpdateFood(food, food.getCategoryId().getId());
        return "redirect:/foods";
    }

    @GetMapping("/foods/delete/{id}")
    public String deleteFood(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        foodSer.deleteFoodById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa thực đơn thành công!");

        return "redirect:/foods"; // Quay về danh sách
    }
}
