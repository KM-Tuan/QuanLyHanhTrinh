/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.FoodOrder;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kieum
 */
public interface FoodOrderService {
    FoodOrder createOrder(List<Map<String, Object>> items, Integer userId, String journeyName);
    List<FoodOrder> getOrderByUserId(int userId);
    List<Object[]> getTotalRevenueByDay();
    List<Object[]> getTotalRevenueByMonth();
    List<Object[]> getTotalRevenueByYear();
}
