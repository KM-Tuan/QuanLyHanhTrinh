/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Train;
import com.kmt.repository.TrainRepository;
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
public class TrainRepositoryImpl implements TrainRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Train> getTrains() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From Train", Train.class);
        return q.getResultList();
    }
}
