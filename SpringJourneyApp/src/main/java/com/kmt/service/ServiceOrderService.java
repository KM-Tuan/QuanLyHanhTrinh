/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.ServiceOrder;
import java.util.List;

/**
 *
 * @author kieum
 */

public interface ServiceOrderService {
    void createServiceOrder(ServiceOrder so);
    boolean existsByUserStationJourney(int userId, int stationId, String journeyName, int serviceId);
    List<ServiceOrder> getServiceOrdersByUserId(int userId);
}
