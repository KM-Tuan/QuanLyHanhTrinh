/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Journey;
import com.kmt.repository.JourneyRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
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
        return q.getSingleResult();
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
    public void deleteJourneyById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Journey j = this.getJourneyById(id);
        s.remove(j);
    }

    @Override
    public List<Journey> searchJourneysByParams(Map<String, String> params) {
        Session s = factory.getObject().getCurrentSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<Journey> q = cb.createQuery(Journey.class);
        Root<Journey> journey = q.from(Journey.class);

        List<Predicate> predicates = new ArrayList<>();

        String name = params.get("name");
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(cb.lower(journey.get("name")), "%" + name.toLowerCase() + "%"));
        }

        String trainName = params.get("trainName");
        if (trainName != null && !trainName.isEmpty()) {
            predicates.add(cb.like(cb.lower(journey.get("trainId").get("name")), "%" + trainName.toLowerCase() + "%"));
        }

        String departureStationName = params.get("departureStationName");
        if (departureStationName != null && !departureStationName.isEmpty()) {
            predicates.add(cb.like(cb.lower(journey.get("departureStationId").get("name")), "%" + departureStationName.toLowerCase() + "%"));
        }

        String arrivalStationName = params.get("arrivalStationName");
        if (arrivalStationName != null && !arrivalStationName.isEmpty()) {
            predicates.add(cb.like(cb.lower(journey.get("arrivalStationId").get("name")), "%" + arrivalStationName.toLowerCase() + "%"));
        }

        String status = params.get("status");
        if (status != null && !status.isEmpty()) {
            predicates.add(cb.equal(cb.lower(journey.get("status").as(String.class)), status.toLowerCase()));
        }

        q.select(journey).where(predicates.toArray(new Predicate[0]));
        return s.createQuery(q).getResultList();
    }
}
