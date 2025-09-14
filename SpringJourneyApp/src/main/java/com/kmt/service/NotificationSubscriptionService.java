/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.Journey;
import com.kmt.pojo.NotificationSubscription;
import com.kmt.pojo.User;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface NotificationSubscriptionService {
    List<NotificationSubscription> findByJourney(String journeyName);
    boolean existsByJourneyAndUser(String journeyName, int userId);
    void add(NotificationSubscription subscription);
}
