/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Journey;
import com.kmt.repository.JourneyRepository;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class JourneyRepositoryImpl implements JourneyRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    public List<Journey> getJours() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Journey.findAll", Journey.class);
        return q.getResultList();
    }

    @Override
    public List<Journey> getJoursCompleted() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Journey> q = s.createNamedQuery("Journey.findCompleted", Journey.class);
        q.setParameter("status", Journey.JourneyStatus.COMPLETED);
        return q.getResultList();
    }
}
