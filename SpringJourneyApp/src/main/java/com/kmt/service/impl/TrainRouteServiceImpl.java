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

        // Lưu ga đi ban đầu nếu xóa route đầu tiên
        Station originalDeparture = null;
        if (deletedStopOrder == 1) {
            originalDeparture = routeToDelete.getDepartureStationId();
        }

        // Xóa route
        trainRouteRepo.deleteRouteById(id);

        // Giảm stopOrder các route sau
        List<TrainRoute> laterRoutes = trainRouteRepo.findRoutesByTrainId(trainId)
                .stream()
                .filter(r -> r.getStopOrder() > deletedStopOrder)
                .sorted(Comparator.comparingInt(TrainRoute::getStopOrder))
                .collect(Collectors.toList());

        for (TrainRoute r : laterRoutes) {
            r.setStopOrder(r.getStopOrder() - 1);
            trainRouteRepo.addOrUpdateRoute(r);
        }

        // Nếu route đầu tiên bị xóa, gán lại ga đi ban đầu cho route mới stopOrder = 1
        if (originalDeparture != null) {
            TrainRoute newFirstRoute = trainRouteRepo.getRouteByTrainIdAndStopOrder(trainId, 1);
            if (newFirstRoute != null) {
                newFirstRoute.setDepartureStationId(originalDeparture);
                trainRouteRepo.addOrUpdateRoute(newFirstRoute);
            }
        }

        // Cập nhật lại liên kết ga
        trainRouteRepo.updateRouteLinks(trainId);
    }

    @Override
    public void addOrUpdateRoute(Integer routeId, int trainId, int departureStationId, int arrivalStationId,
            int distance, LocalTime travelTime, int stopOrder) {

        TrainRoute route = (routeId != null) ? trainRouteRepo.findRouteById(routeId) : new TrainRoute();

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

        // --- Logic dẩy stopOrder ---
        if (routeId == null) { // chỉ khi thêm mới
            trainRouteRepo.shiftStopOrdersUp(trainId, stopOrder);
        }

        // Gán stopOrder cho route mới
        route.setStopOrder(stopOrder);
        trainRouteRepo.addOrUpdateRoute(route);

        // --- Logic cập nhật liên kết ga ---
        trainRouteRepo.updateRouteLinks(trainId);
    }

    @Override
    public TrainRoute getLastRouteByTrainId(int trainId) {
        List<TrainRoute> routes = trainRouteRepo.findLastRouteByTrainId(trainId);
        if (routes != null && !routes.isEmpty()) {
            return routes.get(0);
        }
        return null;
    }

    @Override
    public TrainRoute getRouteByTrainIdAndStopOrder(int trainId, int stopOrder) {
        return trainRouteRepo.getRouteByTrainIdAndStopOrder(trainId, stopOrder);
    }

    @Override
    public TrainRoute findNextRouteByProgress(int trainId, double distanceTraveled, int journeyDepartureId, int journeyArrivalId) {
        return trainRouteRepo.findNextRouteByProgress(trainId, distanceTraveled, journeyDepartureId, journeyArrivalId);
    }
}
