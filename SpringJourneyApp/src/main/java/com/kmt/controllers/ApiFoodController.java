/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Food;
import com.kmt.service.FoodCategoryService;
import com.kmt.service.FoodService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiFoodController {

    @Autowired
    private FoodService foodSer;

    @Autowired
    private FoodCategoryService foodCateSer;

    @GetMapping("/foods")
    public ResponseEntity<Map<String, Object>> listFoods(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "8") int size) {
        try {
            List<Food> foods = foodSer.getFoodsPaginated(page, size);
            long totalItems = foodSer.countFoods();
            int totalPages = (int) Math.ceil((double) totalItems / size);

            Map<String, Object> response = new HashMap<>();
            response.put("foods", foods);
            response.put("currentPage", page);
            response.put("totalItems", totalItems);
            response.put("totalPages", totalPages);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foods/{foodId}")
    public ResponseEntity<?> getFoodById(@PathVariable(name = "foodId") int foodId) {
        Food f = foodSer.getFoodById(foodId);
        return ResponseEntity.ok(f);
    }

    @PutMapping("/foods/update/{foodId}")
    public ResponseEntity<?> updateFood(
            @PathVariable(name = "foodId") int foodId,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam("quantity") int quantity,
            @RequestParam("categoryId") int categoryId,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) {
        Food food = foodSer.getFoodById(foodId);
        if (food == null) {
            return ResponseEntity.notFound().build();
        }

        food.setName(name);
        food.setDescription(description);
        food.setPrice(price);
        food.setQuantity(quantity);
        food.setFile(file);
        foodSer.addOrUpdateFood(food, categoryId);

        return ResponseEntity.ok(food);
    }

    @PostMapping("foods/{id}/decrease-quantity")
    public ResponseEntity<?> decreaseQuantity(
            @PathVariable("id") Integer id,
            @RequestParam("quantityChange") int quantityChange) {

        Food food = foodSer.getFoodById(id);
        
        food.setQuantity(food.getQuantity() - quantityChange);
        foodSer.addOrUpdateFood(food, food.getCategoryId().getId());

        return ResponseEntity.ok(food);
    }

    @PostMapping("foods/{id}/increase-quantity")
    public ResponseEntity<?> increaseQuantity(
            @PathVariable("id") Integer id,
            @RequestParam("quantityChange") int quantityChange) {

        Food food = foodSer.getFoodById(id);

        food.setQuantity(food.getQuantity() + quantityChange);
        foodSer.addOrUpdateFood(food, food.getCategoryId().getId());

        return ResponseEntity.ok(food);
    }

}
