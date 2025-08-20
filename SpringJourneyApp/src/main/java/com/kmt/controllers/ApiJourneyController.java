/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Journey;
import com.kmt.service.JourneyService;
import com.kmt.service.impl.JourneyStatusScheduler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiJourneyController {

    @Autowired
    private JourneyService jourSer;

    @Autowired
    private JourneyStatusScheduler scheduler;

    @GetMapping("/track-journey")
    public ResponseEntity<?> getJourneyProgress(@RequestParam("name") String name) {
        Journey j = jourSer.getJourneyByName(name);

        if (j == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Journey not found");
        }

        int progress = scheduler.calculateProgress(j, LocalDateTime.now());
        
        int traveled = (int) ((j.getTotalDistance() * progress) / 100.0);
        int remaining = j.getTotalDistance() - traveled;

        Map<String, Object> result = new HashMap<>();
        result.put("name", j.getName());
        result.put("progress", progress);
        result.put("status", j.getStatus());
        result.put("remainingDistance", remaining);

        return ResponseEntity.ok(result);
    }

}
