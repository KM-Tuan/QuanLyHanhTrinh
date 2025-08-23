/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.ServiceOrder;
import com.kmt.repository.ServiceOrderRepository;
import com.kmt.service.ServiceOrderService;
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
public class ServiceOrderServiceImpl implements ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceOrderRepo;

    @Override
    public void createServiceOrder(ServiceOrder so) {
        this.serviceOrderRepo.createServiceOrder(so);
    }

    @Override
    public boolean existsByUserStationJourney(int userId, int stationId, String journeyName, int serviceId) {
        return this.serviceOrderRepo.existsByUserStationJourney(userId, stationId, journeyName, serviceId);
    }

    @Override
    public List<ServiceOrder> getServiceOrdersByUserId(int userId) {
        return this.serviceOrderRepo.getServiceOrdersByUserId(userId);
    }

}
