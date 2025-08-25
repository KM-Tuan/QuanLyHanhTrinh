/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.FoodOrder;

/**
 *
 * @author kieum
 */
public interface FoodOrderRepository {
    FoodOrder saveOrder(FoodOrder order);
}
