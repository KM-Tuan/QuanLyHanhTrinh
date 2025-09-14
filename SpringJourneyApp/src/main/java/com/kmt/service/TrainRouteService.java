/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface TrainRouteService {
    List<Train> findTrainsByStations(int departureStationId, int arrivalStationId);
    List<TrainRoute> findByTrainAndStations(int trainId, int departureStationId, int arrivalStationId);
    List<TrainRoute> findRoutesBetweenStations(int trainId, int departureStationId, int arrivalStationId);
    List<TrainRoute> findRoutesByTrainId(int trainId);
    TrainRoute findRouteById(int id);
    void deleteRouteById(int id, int trainId);
    void addOrUpdateRoute(Integer routeId, int trainId, int departureStationId, int arrivalStationId, int distance, LocalTime travelTime, int stopOrder);
    TrainRoute getLastRouteByTrainId(int trainId);
    TrainRoute getRouteByTrainIdAndStopOrder(int trainId, int stopOrder);
    TrainRoute findNextRouteByProgress(int trainId, double distanceTraveled, int journeyDepartureId, int journeyArrivalId);
}
