/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.repository.FoodOrderItemRepository;
import com.kmt.service.FoodOrderItemService;
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
public class FoodOrderItemServiceImpl implements FoodOrderItemService{
    
    @Autowired
    private FoodOrderItemRepository foodOrderRepo;
    
    @Override
    public List<Object[]> getMostOrderedByJourney() {
        return foodOrderRepo.getMostOrderedByJourney();
    }
}
