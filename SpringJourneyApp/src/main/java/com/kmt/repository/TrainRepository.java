/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.Train;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface TrainRepository {
    List<Train> getTrains();
    Train getTrainById(int id);
    List<Train> getTrainsPaginated(int page, int size);
    long countTrains();
    void addOrUpdateTrain(Train train);
    void deleteTrainById(int id);
}
