/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Journey;
import com.kmt.pojo.Station;
import com.kmt.pojo.TrainRoute;
import com.kmt.service.JourneyService;
import com.kmt.service.TrainRouteService;
import java.util.ArrayList;
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
public class ApiTrainRouteController {

    @Autowired
    private JourneyService jourSer;

    @Autowired
    private TrainRouteService trainRouteSer;

    @GetMapping("/journeys/{journeyName}/stations")
    public ResponseEntity<List<Station>> getStationsByJourneyName(@PathVariable("journeyName") String journeyName) {
        Journey journey = jourSer.getJourneyByName(journeyName);
        if (journey == null) {
            return ResponseEntity.notFound().build();
        }

        int trainId = journey.getTrainId().getId();
        int departureStationId = journey.getDepartureStationId().getId();
        int arrivalStationId = journey.getArrivalStationId().getId();

        List<TrainRoute> routes = trainRouteSer.findRoutesBetweenStations(trainId, departureStationId, arrivalStationId);

        List<Station> stations = new ArrayList<>();

        for (int i = 0; i < routes.size(); i++) {
            TrainRoute tr = routes.get(i);

            Station dep = tr.getDepartureStationId();
            dep.setDistance(tr.getDistance()); // distance tới ga kế tiếp
            if (stations.stream().noneMatch(s -> s.getId().equals(dep.getId()))) {
                stations.add(dep);
            }

            Station arr = tr.getArrivalStationId();
            // arr là ga cuối của đoạn này => distance sẽ được gán khi nó là departure của đoạn sau
            if (stations.stream().noneMatch(s -> s.getId().equals(arr.getId()))) {
                stations.add(arr);
            }
        }

        return ResponseEntity.ok(stations);
    }

}
