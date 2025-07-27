/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Food;
import com.kmt.service.FoodService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author kieum
 */
@Controller
public class FoodController {

    @Autowired
    private FoodService foodSer;

    @GetMapping("/foods")
    public String showFoods(Model model) {
        List<Food> allFoods = foodSer.getFoods();
        List<Food> drinks = allFoods.stream()
                .filter(f -> f.getCategoryId().getId() == 1)
                .collect(Collectors.toList());

        List<Food> foods = allFoods.stream()
                .filter(f -> f.getCategoryId().getId() == 2)
                .collect(Collectors.toList());

        model.addAttribute("drinks", drinks);
        model.addAttribute("foods", foods);
        return "foods"; // tên file HTML
    }
}
