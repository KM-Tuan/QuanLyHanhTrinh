/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface TrainRouteService {
    List<Train> findTrainsByStations(int departureStationId, int arrivalStationId);
    List<TrainRoute> findByTrainAndStations(int trainId, int departureStationId, int arrivalStationId);
}
