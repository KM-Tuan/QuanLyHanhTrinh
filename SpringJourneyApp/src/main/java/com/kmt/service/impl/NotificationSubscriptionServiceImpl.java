/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Journey;
import com.kmt.pojo.NotificationSubscription;
import com.kmt.pojo.User;
import com.kmt.repository.NotificationSubscriptionRepository;
import com.kmt.service.NotificationSubscriptionService;
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
public class NotificationSubscriptionServiceImpl implements NotificationSubscriptionService{
    
    @Autowired
    private NotificationSubscriptionRepository notiRepo;
    
    @Override
    public List<NotificationSubscription> findByJourney(String journeyName) {
        return this.notiRepo.findByJourney(journeyName);
    }

    @Override
    public boolean existsByJourneyAndUser(String journeyName, int userId) {
        return this.notiRepo.existsByJourneyAndUser(journeyName, userId);
    }

    @Override
    public void add(NotificationSubscription subscription) {
        this.notiRepo.add(subscription);
    }
    
}
