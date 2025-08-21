/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Station;
import com.kmt.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author kieum
 */
@RestController
@RequestMapping("/api")
public class ApiStationController {
    
    @Autowired
    private StationService staSer;
    
    @GetMapping("/stations/{stationId}")
    public ResponseEntity<Station> getStationById(@PathVariable("stationId") Integer stationId) {
        Station s = staSer.getStationById(stationId);
            return ResponseEntity.ok(s);
    }
    
}
