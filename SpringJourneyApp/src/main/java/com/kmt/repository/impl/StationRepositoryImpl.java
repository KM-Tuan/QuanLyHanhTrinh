/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Station;
import com.kmt.repository.StationRepository;
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
public class StationRepositoryImpl implements StationRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Station> getStas() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Station.findAll", Station.class);
        return q.getResultList();
    }

    @Override
    public Station getStationById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Station> q = s.createNamedQuery("Station.findById", Station.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public void addOrUpdateStation(Station station) {
        Session s = this.factory.getObject().getCurrentSession();

        if (station.getId() == null) {
            s.persist(station);
        } else {
            s.merge(station);
        }
    }
    
    @Override
    public void deleteStationById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Station station = this.getStationById(id);
        s.remove(station);
    }
}
