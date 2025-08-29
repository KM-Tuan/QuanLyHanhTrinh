/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Station;
import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import com.kmt.repository.TrainRepository;
import com.kmt.repository.TrainRouteRepository;
import com.kmt.service.StationService;
import com.kmt.service.TrainRouteService;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class TrainRouteServiceImpl implements TrainRouteService {

    @Autowired
    private TrainRouteRepository trainRouteRepo;

    @Autowired
    private TrainRepository trainRepo;

    @Autowired
    private StationService staSer;

    @Override
    public List<Train> findTrainsByStations(int departureStationId, int arrivalStationId) {
        return this.trainRouteRepo.findTrainsByStations(departureStationId, arrivalStationId);
    }

    @Override
    public List<TrainRoute> findByTrainAndStations(int trainId, int departureStationId, int arrivalStationId) {
        return trainRouteRepo.findByTrainAndStations(trainId, departureStationId, arrivalStationId);
    }

    @Override
    public List<TrainRoute> findRoutesBetweenStations(int trainId, int departureStationId, int arrivalStationId) {
        return this.trainRouteRepo.findRoutesBetweenStations(trainId, departureStationId, arrivalStationId);
    }

    @Override
    public List<TrainRoute> findRoutesByTrainId(int trainId) {
        return this.trainRouteRepo.findRoutesByTrainId(trainId);
    }

    @Override
    public TrainRoute findRouteById(int id) {
        return this.trainRouteRepo.findRouteById(id);
    }

    @Override
    public void deleteRouteById(int id, int trainId) {
        TrainRoute routeToDelete = trainRouteRepo.findRouteById(id);
        int deletedStopOrder = routeToDelete.getStopOrder();

        trainRouteRepo.deleteRouteById(id);

        List<TrainRoute> laterRoutes = trainRouteRepo.findRoutesByTrainId(trainId)
                .stream()
                .filter(r -> r.getStopOrder() > deletedStopOrder)
                .sorted(Comparator.comparingInt(TrainRoute::getStopOrder))
                .collect(Collectors.toList());

        for (TrainRoute r : laterRoutes) {
            r.setStopOrder(r.getStopOrder() - 1);
            trainRouteRepo.addOrUpdateRoute(r);
        }
    }

    @Override
    public void addOrUpdateRoute(Integer routeId, int trainId, int departureStationId, int arrivalStationId,
            int distance, LocalTime travelTime, int stopOrder) {

        TrainRoute route = (routeId != null) ? findRouteById(routeId) : new TrainRoute();

        // Set Train
        Train train = trainRepo.getTrainById(trainId);
        route.setTrainId(train);

        // Set Stations
        Station dep = staSer.getStationById(departureStationId);
        Station arr = staSer.getStationById(arrivalStationId);
        route.setDepartureStationId(dep);
        route.setArrivalStationId(arr);

        // Set các field khác
        route.setDistance(distance);
        route.setTravelTime(travelTime);
        route.setStopOrder(stopOrder);

        // Lưu/Update
        trainRouteRepo.addOrUpdateRoute(route);
    }

    @Override
    public TrainRoute getLastRouteByTrainId(int trainId) {
        List<TrainRoute> routes = trainRouteRepo.findLastRouteByTrainId(trainId);
        if (routes != null && !routes.isEmpty()) {
            return routes.get(0);
        }
        return null;
    }
}
