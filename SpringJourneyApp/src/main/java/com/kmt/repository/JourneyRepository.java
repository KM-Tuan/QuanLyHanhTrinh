/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.Journey;
import java.util.List;


/**
 *
 * @author kieum
 */
public interface JourneyRepository {
    List<Journey> getJours();
    List<Journey> getJoursCompleted();
    void saveJourney(Journey journey);
    boolean isNameExists(String name);
    Journey getJourneyByName(String name);
    List<Journey> findByStatusNot(Journey.JourneyStatus status);
}
