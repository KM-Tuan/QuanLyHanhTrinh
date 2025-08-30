/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface TrainRouteRepository {
    List<Train> findTrainsByStations(int departureStationId, int arrivalStationId);
    List<TrainRoute> findByTrainAndStations(int trainId, int departureStationId, int arrivalStationId);
    List<TrainRoute> findRoutesBetweenStations(int trainId, int departureStationId, int arrivalStationId);
    List<TrainRoute> findRoutesByTrainId(int trainId);
    TrainRoute findRouteById(int id);
    void deleteRouteById(int id);
    void addOrUpdateRoute(TrainRoute rou);
    List<TrainRoute> findLastRouteByTrainId(int trainId);
    void shiftStopOrdersUp(int trainId, int stopOrder);
    void updateRouteLinks(int trainId);
}
