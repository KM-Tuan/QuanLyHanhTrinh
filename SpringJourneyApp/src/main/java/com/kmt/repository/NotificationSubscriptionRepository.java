/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.NotificationSubscription;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface NotificationSubscriptionRepository {
    List<NotificationSubscription> findByJourney(String journeyName);
    boolean existsByJourneyAndUser(String journeyName, int userId);
    void add(NotificationSubscription subscription);
}
