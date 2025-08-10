/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.FoodCategory;
import com.kmt.repository.FoodCategoryRepository;
import com.kmt.service.FoodCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class FoodCategoryServiceImpl implements FoodCategoryService {

    @Autowired
    private FoodCategoryRepository cateRepo;

    @Override
    public List<FoodCategory> getCate() {
        return this.cateRepo.getCate();
    }

    @Override
    public FoodCategory getCategoryById(int id) {
        return this.cateRepo.getCategoryById(id);
    }

    @Override
    public void addOrUpdateCategory(FoodCategory fc) {
        this.cateRepo.addOrUpdateCategory(fc);
    }

    @Override
    public void deleteCategoryById(int id) {
        this.cateRepo.deleteCategoryById(id);
    }

}
