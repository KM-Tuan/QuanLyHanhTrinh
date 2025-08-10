/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.FoodCategory;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface FoodCategoryService {
    List<FoodCategory> getCate();
    FoodCategory getCategoryById(int id);
    void addOrUpdateCategory(FoodCategory fc);
    void deleteCategoryById(int id);
}
