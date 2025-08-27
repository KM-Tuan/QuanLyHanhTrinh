/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.ServiceOrder;
import com.kmt.repository.ServiceOrderRepository;
import jakarta.persistence.NoResultException;
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
public class ServiceOrderRepositoryImpl implements ServiceOrderRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void createServiceOrder(ServiceOrder so) {
        Session s = this.factory.getObject().getCurrentSession();

        if (so.getId() == null) {
            s.persist(so);
        } else {
            s.merge(so);
        }
    }

    @Override
    public boolean existsByUserStationJourney(int userId, int stationId, String journeyName, int serviceId) {
        try {
            Session s = this.factory.getObject().getCurrentSession();
            Query<ServiceOrder> q = s.createNamedQuery("ServiceOrder.existServiceOrder", ServiceOrder.class);
            q.setParameter("userId", userId);
            q.setParameter("stationId", stationId);
            q.setParameter("journeyName", journeyName);
            q.setParameter("serviceId", serviceId);
            q.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    
    @Override
    public List<ServiceOrder> getServiceOrdersByUserId(int userId) {
        Session s = factory.getObject().getCurrentSession();
        Query<ServiceOrder> query = s.createNamedQuery("ServiceOrder.findByUserId", ServiceOrder.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

}
