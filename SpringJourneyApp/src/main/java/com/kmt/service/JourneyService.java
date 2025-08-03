/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.Journey;
import com.kmt.pojo.Train;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface JourneyService {
    List<Journey> getJours();
    List<Journey> getJoursCompleted();
    Journey getJourneyById(int id);
    void addOrUpdateJourney(Journey journey);
    boolean isNameExists(String name);
    String generateRandomName();
    List<Journey> getAllJourneysNotCompleted();
}
