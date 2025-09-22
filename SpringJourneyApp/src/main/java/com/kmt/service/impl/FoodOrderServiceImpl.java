/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.FoodOrder;
import com.kmt.pojo.FoodOrderItem;
import com.kmt.pojo.Journey;
import com.kmt.pojo.User;
import com.kmt.repository.FoodOrderRepository;
import com.kmt.repository.FoodRepository;
import com.kmt.repository.JourneyRepository;
import com.kmt.repository.UserRepository;
import com.kmt.service.FoodOrderService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class FoodOrderServiceImpl implements FoodOrderService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JourneyRepository jourRepo;

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private FoodOrderRepository orderRepo;

    @Override
    public FoodOrder createOrder(List<Map<String, Object>> items, Integer userId, String journeyName) {
        User user = userRepo.getUserById(userId);
        Journey journey = jourRepo.getJourneyByName(journeyName);

        FoodOrder order = new FoodOrder();
        order.setCreatedAt(LocalDateTime.now());
        order.setUserId(user);
        order.setJourneyName(journey);
        order.setName("Đơn hàng " + LocalDateTime.now());

        double total = 0;

        for (Map<String, Object> i : items) {
            FoodOrderItem item = new FoodOrderItem();
            item.setFoodId(foodRepo.getFoodById((Integer) i.get("id")));
            item.setQuantity((Integer) i.get("quantity"));
            item.setPrice(((Number) i.get("price")).doubleValue());
            item.setFoodOrderId(order);

            total += item.getQuantity() * item.getPrice();
            order.getFoodOrderItemList().add(item);
        }

        order.setTotalAmount(total);

        return orderRepo.saveOrder(order);
    }

    @Override
    public List<Object[]> getTotalRevenueByDay() {
        return this.orderRepo.getTotalRevenueByDay();
    }

    @Override
    public List<Object[]> getTotalRevenueByMonth() {
        return this.orderRepo.getTotalRevenueByMonth();
    }

    @Override
    public List<Object[]> getTotalRevenueByYear() {
        return this.orderRepo.getTotalRevenueByYear();
    }

    @Override
    public List<FoodOrder> getOrderByUserId(int userId) {
        return this.orderRepo.getOrderByUserId(userId);
    }

}
