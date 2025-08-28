package com.kmt.controllers;

import com.kmt.pojo.FoodCategory;
import com.kmt.service.FoodCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiFoodCategoryController {

    @Autowired
    private FoodCategoryService foodSer;

    @GetMapping("/food-categories")
    public List<FoodCategory> listCategories() {
        return foodSer.getCate();
    }

}
