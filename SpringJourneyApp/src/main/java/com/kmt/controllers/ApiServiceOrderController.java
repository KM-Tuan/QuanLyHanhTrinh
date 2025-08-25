/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Journey;
import com.kmt.pojo.Service;
import com.kmt.pojo.ServiceOrder;
import com.kmt.pojo.Station;
import com.kmt.pojo.User;
import com.kmt.service.JourneyService;
import com.kmt.service.ServiceOrderService;
import com.kmt.service.ServiceService;
import com.kmt.service.StationService;
import com.kmt.service.UserService;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiServiceOrderController {

    @Autowired
    private ServiceOrderService serviceOrderSer;

    @Autowired
    private UserService userSer;

    @Autowired
    private ServiceService serSer;

    @Autowired
    private StationService staSer;

    @Autowired
    private JourneyService jourSer;

    @PostMapping("/service-register")
    public ResponseEntity<?> registerService(
            @RequestParam("userId") Integer userId,
            @RequestParam("serviceId") Integer serviceId,
            @RequestParam("stationId") Integer stationId,
            @RequestParam("journeyName") String journeyName) {

        User user = userSer.getUserById(userId);
        Service service = serSer.getServiceById(serviceId);
        Station station = staSer.getStationById(stationId);
        Journey journey = jourSer.getJourneyByName(journeyName);
        
        boolean exists = serviceOrderSer.existsByUserStationJourney(userId, stationId, journeyName, serviceId);

        if (exists) {
            return ResponseEntity.badRequest().body("Bạn đã đăng ký dịch vụ này rồi!");
        }
        
        ServiceOrder order = new ServiceOrder();
        order.setServiceId(service);
        order.setStationId(station);
        order.setJourneyName(journey);
        order.setUserId(user);
        order.setName(service.getName());
        order.setCreatedAt(LocalDateTime.now());

        serviceOrderSer.createServiceOrder(order);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/service-register/user/{userId}")
    public ResponseEntity<?> getServiceOrdersByUser(@PathVariable("userId") Integer userId) {
        List<ServiceOrder> orders = serviceOrderSer.getServiceOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

}
