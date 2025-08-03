/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.Journey;
import java.util.List;
import java.util.Map;


/**
 *
 * @author kieum
 */
public interface JourneyRepository {
    List<Journey> getJours();
    List<Journey> getJoursCompleted();
    void addOrUpdateJourney(Journey journey);
    boolean isNameExists(String name);
    Journey getJourneyByName(String name);
    Journey getJourneyById(int id);
    List<Journey> findByStatusNot(Journey.JourneyStatus status);
    boolean deleteJourneyById(int id);
    public List<Journey> searchJourneysByParams(Map<String, String> params);
}
