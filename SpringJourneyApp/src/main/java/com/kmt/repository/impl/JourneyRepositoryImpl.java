/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Journey;
import com.kmt.pojo.Station;
import com.kmt.pojo.Train;
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

    @Override
    public void addOrUpdateJourney(Journey journey) {
        Session s = this.factory.getObject().getCurrentSession();

        if (journey.getId() == null) {
            s.persist(journey);
        } else {
            s.merge(journey);
        }
    }

    @Override
    public boolean isNameExists(String name) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Journey> q = s.createNamedQuery("Journey.findByName", Journey.class);
        q.setParameter("name", name);
        return !q.getResultList().isEmpty();
    }

    @Override
    public Journey getJourneyByName(String name) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Journey> q = s.createNamedQuery("Journey.findByName", Journey.class);
        q.setParameter("name", name);
        List<Journey> list = q.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Journey getJourneyById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Journey> q = s.createNamedQuery("Journey.findById", Journey.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public List<Journey> findByStatusNot(Journey.JourneyStatus status) {
        Session s = factory.getObject().getCurrentSession();
        Query<Journey> q = s.createNamedQuery("Journey.findByStatusNot", Journey.class);
        q.setParameter("status", status);
        return q.getResultList();
    }

    @Override
    public boolean deleteJourneyById(int id) {
        Session s = factory.getObject().getCurrentSession();
        int result = s.createNamedQuery("Journey.deleteById")
                            .setParameter("id", id)
                            .executeUpdate();
        return result > 0;
    }
}
