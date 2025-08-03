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
import java.util.Map;
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
    public void addOrUpdateJourney(Journey journey) {
        this.jourRepo.addOrUpdateJourney(journey);
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
        return this.jourRepo.findByStatusNot(Journey.JourneyStatus.COMPLETED);
    }

    @Override
    public Journey getJourneyById(int id) {
        return this.jourRepo.getJourneyById(id);
    }
    
    @Override
    public boolean deleteJourneyById(int id) {
        return this.jourRepo.deleteJourneyById(id);
    }

    @Override
    public List<Journey> searchJourneysByParams(Map<String, String> params) {
        return this.jourRepo.searchJourneysByParams(params);
    }

}
