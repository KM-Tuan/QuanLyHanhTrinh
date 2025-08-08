/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.Service;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface ServiceRepository {
    List<Service> getServicesByStationId(int stationId);
}
