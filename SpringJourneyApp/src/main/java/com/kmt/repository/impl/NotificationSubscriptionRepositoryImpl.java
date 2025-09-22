/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Journey;
import com.kmt.pojo.NotificationSubscription;
import com.kmt.pojo.User;
import com.kmt.repository.NotificationSubscriptionRepository;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Repository
@Transactional
public class NotificationSubscriptionRepositoryImpl implements NotificationSubscriptionRepository{

    @Autowired
    private LocalSessionFactoryBean factory;

    
    @Override
    public List<NotificationSubscription> findByJourney(String journeyName) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.createNamedQuery("NotificationSubscription.findByJourney", NotificationSubscription.class)
                .setParameter("journeyName", journeyName)
                .getResultList();
    }

    
    @Override
    public boolean existsByJourneyAndUser(String journeyName, int userId) {
        Session s = this.factory.getObject().getCurrentSession();
        Boolean result = s.createNamedQuery("NotificationSubscription.existsByJourneyAndUser", Boolean.class)
                .setParameter("journeyName", journeyName)
                .setParameter("userId", userId)
                .getSingleResult();
        return result != null && result;
    }

    // Thêm mới subscription
    @Override
    public void add(NotificationSubscription subscription) {
        Session s = this.factory.getObject().getCurrentSession();
        
        if (subscription.getId() == null) {
            s.persist(subscription);
        } else {
            s.merge(subscription);
        }
    }
}
