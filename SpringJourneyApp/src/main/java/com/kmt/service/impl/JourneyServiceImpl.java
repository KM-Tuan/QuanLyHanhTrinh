/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Journey;
import com.kmt.pojo.Train;
import com.kmt.repository.JourneyRepository;
import com.kmt.service.JourneyService;
import com.kmt.service.TrainService;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    private JourneyRepository jourRepo;

    @Autowired
    private TrainService trainService;

    @Override
    public List<Journey> getJours() {
        return this.jourRepo.getJours();
    }

    @Override
    public List<Journey> getJoursCompleted() {
        return this.jourRepo.getJoursCompleted();
    }

    @Override
    public void saveJourney(Journey journey) {
        this.jourRepo.saveJourney(journey);
    }

    @Override
    public String generateRandomName() {
        Random rand = new Random();
        String prefix = "JRN";
        String name;
        do {
            int number = rand.nextInt(1000); // 0 - 999
            name = String.format("%s%03d", prefix, number);
        } while (isNameExists(name));
        return name;
    }

    @Override
    public boolean isNameExists(String name) {
        return this.jourRepo.isNameExists(name);
    }

    @Override
    public List<Journey> getAllJourneysNotCompleted() {
        return jourRepo.findByStatusNot(Journey.JourneyStatus.COMPLETED);
    }

}
