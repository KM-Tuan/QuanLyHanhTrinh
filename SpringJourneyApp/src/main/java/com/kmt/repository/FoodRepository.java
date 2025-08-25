/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.Food;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface FoodRepository {
    List<Food> getFoods();
    List<Food> getFoodsPaginated(int page, int size);
    long countFoods();
    void addOrUpdateFood(Food f);
    Food getFoodById(int id);
    void deleteFoodById(int id);
}
