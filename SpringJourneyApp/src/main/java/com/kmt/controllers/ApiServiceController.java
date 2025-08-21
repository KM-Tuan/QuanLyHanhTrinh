/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Service;
import com.kmt.service.ServiceService;
import java.util.List;
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
public class ApiServiceController {

    @Autowired
    private ServiceService serSer;

    @GetMapping("/stations/{stationId}/services")
    public ResponseEntity<List<Service>> getServicesByStationId(@PathVariable("stationId") int stationId) {
        List<Service> services = serSer.getServicesByStationId(stationId);
        return ResponseEntity.ok(services);
    }
}
