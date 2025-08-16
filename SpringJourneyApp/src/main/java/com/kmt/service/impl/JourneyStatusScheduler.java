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

    private static final int SPEED_MULTIPLIER = 3600; // 1 phút thực = 1 giờ ảo

    @Scheduled(fixedRate = 1000)
    @Transactional
    public void updateJourneyStatuses() {
        List<Journey> journeys = journeySer.getAllJourneysNotCompleted();
        LocalDateTime now = LocalDateTime.now();

        for (Journey j : journeys) {
            LocalDateTime dep = j.getDepartureTime();
            LocalDateTime arr = j.getArrivalTime();

            if (now.isBefore(dep)) {
                // Chưa tới giờ xuất phát → luôn WAITING
                j.setStatus(Journey.JourneyStatus.WAITTING);
            } else if (now.isAfter(arr)) {
                // Đã qua giờ kết thúc → COMPLETED
                j.setStatus(Journey.JourneyStatus.COMPLETED);
            } else {
                // Đang chạy → tính thời gian ảo
                long realMinutesElapsed = java.time.Duration.between(dep, now).toMinutes();
                long virtualMinutesElapsed = realMinutesElapsed * SPEED_MULTIPLIER;

                LocalDateTime virtualNow = dep.plusMinutes(virtualMinutesElapsed);

                if (virtualNow.isAfter(arr)) {
                    j.setStatus(Journey.JourneyStatus.COMPLETED);
                } else {
                    j.setStatus(Journey.JourneyStatus.RUNNING);
                }
            }

            journeySer.addOrUpdateJourney(j);
        }
    }
}
