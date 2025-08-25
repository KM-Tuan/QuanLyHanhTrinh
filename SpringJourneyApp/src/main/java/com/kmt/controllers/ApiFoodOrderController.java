/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.FoodOrder;
import com.kmt.service.FoodOrderService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiFoodOrderController {

    @Autowired
    private FoodOrderService oderSer;
    
    @PostMapping("/cart")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FoodOrder> checkout(@RequestBody Map<String, Object> payload) {
        Integer userId = (Integer) payload.get("userId");
        String journeyName = (String) payload.get("journeyName");
        List<Map<String, Object>> items = (List<Map<String, Object>>) payload.get("items");

        FoodOrder saved = oderSer.createOrder(items, userId, journeyName);
        return ResponseEntity.ok(saved);
    }

}
