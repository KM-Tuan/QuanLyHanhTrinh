/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Service;
import com.kmt.repository.ServiceRepository;
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
public class ServiceRepositoryImpl implements ServiceRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Service> getServicesByStationId(int stationId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Service> q = s.createNamedQuery("Service.findByStationId", Service.class);
        q.setParameter("stationId", stationId);
        List<Service> list = q.getResultList();
        return q.getResultList();
    }

}
