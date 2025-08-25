/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kmt.pojo.Food;
import com.kmt.pojo.FoodCategory;
import com.kmt.repository.FoodCategoryRepository;
import com.kmt.repository.FoodRepository;
import com.kmt.service.FoodService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepo;
    
    @Autowired
    private FoodCategoryRepository cateRepo;
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Food> getFoods() {
        return this.foodRepo.getFoods();
    }

    @Override
    public Food getFoodById(int id) {
        return this.foodRepo.getFoodById(id);
    }

    @Override
    public void addOrUpdateFood(Food food, int categoryId) {
        // Lấy category và gán vào food
        FoodCategory category = cateRepo.getCategoryById(categoryId);
        food.setCategoryId(category);

        Food targetFood;

        if (food.getId() != null) { // Update
            targetFood = foodRepo.getFoodById(food.getId());
            targetFood.setName(food.getName());
            targetFood.setDescription(food.getDescription());
            targetFood.setPrice(food.getPrice());
            targetFood.setQuantity(food.getQuantity());
            targetFood.setCategoryId(category);
        } else { // Thêm mới
            targetFood = food;
        }

        // Xử lý upload ảnh nếu có
        if (food.getFile() != null && !food.getFile().isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(food.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                targetFood.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(FoodServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        foodRepo.addOrUpdateFood(targetFood);
    }

    @Override
    public void deleteFoodById(int id) {
        this.foodRepo.deleteFoodById(id);
    }

    @Override
    public List<Food> getFoodsPaginated(int page, int size) {
        return this.foodRepo.getFoodsPaginated(page, size);
    }

    @Override
    public long countFoods() {
        return this.foodRepo.countFoods();
    }

}
