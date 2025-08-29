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
        Query q = s.createNamedQuery("Train.findAll", Train.class);
        return q.getResultList();
    }

    @Override
    public Train getTrainById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<Train> q = session.createNamedQuery("Train.findById", Train.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
    @Override
    public List<Train> getTrainsPaginated(int page, int size) {
        Session session = factory.getObject().getCurrentSession();
        Query<Train> q = session.createNamedQuery("Train.findAllPaginated", Train.class);

        q.setFirstResult(page * size);
        q.setMaxResults(size);

        return q.getResultList();
    }

    @Override
    public long countTrains() {
        Session session = factory.getObject().getCurrentSession();
        Query<Long> q = session.createNamedQuery("Train.countAll", Long.class);
        return q.getSingleResult();
    }
    
    @Override
    public void addOrUpdateTrain(Train train) {
        Session s = this.factory.getObject().getCurrentSession();

        if (train.getId() == null) {
            s.persist(train);
        } else {
            s.merge(train);
        }
    }
    
    @Override
    public void deleteTrainById(int id) {
        Session s = factory.getObject().getCurrentSession();
        Train train = this.getTrainById(id);
        s.remove(train);
    }

}
