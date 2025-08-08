/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Service;
import com.kmt.repository.ServiceRepository;
import com.kmt.service.ServiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService{
    
    @Autowired
    private ServiceRepository serRepo;

    @Override
    public List<Service> getServicesByStationId(int stationId) {
        return this.serRepo.getServicesByStationId(stationId);
    }
    
    
}
