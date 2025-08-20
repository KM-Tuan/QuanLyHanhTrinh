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
public class JourneyStatusScheduler {

    @Autowired
    private JourneyService journeySer;

    // 1 phút thực = 1 giờ ảo (1 giây thực = 60 giây ảo)
    private static final int SPEED_MULTIPLIER = 60;

    public int calculateProgress(Journey j, LocalDateTime now) {
        LocalDateTime dep = j.getDepartureTime();
        LocalDateTime arr = j.getArrivalTime();

        if (now.isBefore(dep)) {
            return 0;
        } else if (now.isAfter(arr)) {
            return 100;
        } else {
            long realSecondsElapsed = java.time.Duration.between(dep, now).getSeconds();
            long virtualSecondsElapsed = realSecondsElapsed * SPEED_MULTIPLIER;
            long totalVirtualSeconds = java.time.Duration.between(dep, arr).getSeconds();

            int progress = (int) ((virtualSecondsElapsed * 100) / totalVirtualSeconds);
            return Math.min(Math.max(progress, 0), 100);
        }
    }

    @Scheduled(fixedRate = 500)
    @Transactional
    public void updateJourneyStatuses() {
        List<Journey> journeys = journeySer.getAllJourneysNotCompleted();
        LocalDateTime now = LocalDateTime.now();

        for (Journey j : journeys) {
            int progress = calculateProgress(j, now);

            if (progress == 0) {
                j.setStatus(Journey.JourneyStatus.WAITTING);
            } else if (progress >= 100) {
                j.setStatus(Journey.JourneyStatus.COMPLETED);
            } else {
                j.setStatus(Journey.JourneyStatus.RUNNING);
            }

            journeySer.addOrUpdateJourney(j);
        }
    }
}
