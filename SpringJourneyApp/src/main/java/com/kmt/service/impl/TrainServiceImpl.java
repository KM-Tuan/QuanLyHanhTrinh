/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Train;
import com.kmt.repository.TrainRepository;
import com.kmt.service.TrainService;
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
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepo;

    @Override
    public List<Train> getTrains() {
        return this.trainRepo.getTrains();
    }

    @Override
    public Train getTrainById(int id) {
        return trainRepo.getTrainById(id);
    }

    @Override
    public List<Train> getTrainsPaginated(int page, int size) {
        return this.trainRepo.getTrainsPaginated(page, size);
    }

    @Override
    public long countTrains() {
        return this.trainRepo.countTrains();
    }
    
    @Override
    public void addOrUpdateTrain(Train t) {
        Train targetTrain = (t.getId() != null) ? trainRepo.getTrainById(t.getId()) : t;

        // Nếu update thì set lại các trường cần thay đổi
        if (t.getId() != null && targetTrain != null) {
            targetTrain.setName(t.getName());
        }

        trainRepo.addOrUpdateTrain(targetTrain);
    }

    @Override
    public void deleteTrainById(int id) {
        this.trainRepo.deleteTrainById(id);
    }

}
