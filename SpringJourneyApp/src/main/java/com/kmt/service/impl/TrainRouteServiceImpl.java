/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import com.kmt.repository.TrainRouteRepository;
import com.kmt.service.TrainRouteService;
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
public class TrainRouteServiceImpl implements TrainRouteService {

    @Autowired
    private TrainRouteRepository trainRouteRepo;

    @Override
    public List<Train> findTrainsByStations(int departureStationId, int arrivalStationId) {
        return this.trainRouteRepo.findTrainsByStations(departureStationId, arrivalStationId);
    }

    @Override
    public List<TrainRoute> findByTrainAndStations(int trainId, int departureStationId, int arrivalStationId) {
        return trainRouteRepo.findByTrainAndStations(trainId, departureStationId, arrivalStationId);
    }

}
