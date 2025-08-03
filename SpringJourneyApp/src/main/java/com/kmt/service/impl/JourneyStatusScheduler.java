/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Journey;
import com.kmt.service.JourneyService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class JourneyStatusScheduler {

    @Autowired
    private JourneyService journeySer;

    private static final int SPEED_MULTIPLIER = 60; // 1 phút thực = 1 giây giả

    @Scheduled(fixedRate = 5000)
    public void updateJourneyStatuses() {
        LocalDateTime now = LocalDateTime.now().plusMinutes(SPEED_MULTIPLIER); // giả lập thời gian trôi nhanh hơn
        List<Journey> journeys = journeySer.getAllJourneysNotCompleted();

        for (Journey j : journeys) {
            if (now.isBefore(j.getDepartureTime())) {
                j.setStatus(Journey.JourneyStatus.WAITTING);
            } else if (now.isAfter(j.getArrivalTime())) {
                j.setStatus(Journey.JourneyStatus.COMPLETED);
            } else {
                j.setStatus(Journey.JourneyStatus.RUNNING);
            }
            journeySer.saveJourney(j);
        }
    }
}
