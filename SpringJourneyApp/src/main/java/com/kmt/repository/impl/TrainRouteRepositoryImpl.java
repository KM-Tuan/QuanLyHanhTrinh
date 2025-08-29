/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import com.kmt.repository.TrainRouteRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class TrainRouteRepositoryImpl implements TrainRouteRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Train> findTrainsByStations(int departureStationId, int arrivalStationId) {
        Session s = this.factory.getObject().getCurrentSession();

        // Truy vấn ga liền kề trong cùng 1 đoạn (NamedQuery: "TrainRoute.findTrainsByAdjacentStations")
        Query<Train> q1 = s.createNamedQuery("TrainRoute.findTrainsByAdjacentStations", Train.class);
        q1.setParameter("departureStationId", departureStationId);
        q1.setParameter("arrivalStationId", arrivalStationId);
        List<Train> list1 = q1.getResultList();

        // Truy vấn ga không liền kề (NamedQuery: "TrainRoute.findTrainsByStations")
        Query<Train> q2 = s.createNamedQuery("TrainRoute.findTrainsByStations", Train.class);
        q2.setParameter("departureStationId", departureStationId);
        q2.setParameter("arrivalStationId", arrivalStationId);
        List<Train> list2 = q2.getResultList();

        // Kết hợp 2 kết quả, tránh trùng lặp
        Set<Train> resultSet = new HashSet<>();
        resultSet.addAll(list1);
        resultSet.addAll(list2);

        return new ArrayList<>(resultSet);
    }

    @Override
    public List<TrainRoute> findByTrainAndStations(int trainId, int departureStationId, int arrivalStationId) {
        Session session = factory.getObject().getCurrentSession();

        Query<TrainRoute> query = session.createNamedQuery("TrainRoute.findRoutesBetweenStations", TrainRoute.class);
        query.setParameter("trainId", trainId);
        query.setParameter("departureStationId", departureStationId);
        query.setParameter("arrivalStationId", arrivalStationId);

        return query.getResultList();
    }

    @Override
    public List<TrainRoute> findRoutesBetweenStations(int trainId, int departureStationId, int arrivalStationId) {
        Session session = factory.getObject().getCurrentSession();
        Query<TrainRoute> query = session.createNamedQuery("TrainRoute.findRoutesBetweenStations", TrainRoute.class);
        query.setParameter("trainId", trainId);
        query.setParameter("departureStationId", departureStationId);
        query.setParameter("arrivalStationId", arrivalStationId);
        return query.getResultList();
    }

    @Override
    public List<TrainRoute> findRoutesByTrainId(int trainId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<TrainRoute> q = session.createNamedQuery("TrainRoute.findByTrainId", TrainRoute.class);
        q.setParameter("trainId", trainId);
        return q.getResultList();
    }

    @Override
    public TrainRoute findRouteById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<TrainRoute> q = session.createNamedQuery("TrainRoute.findById", TrainRoute.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public void deleteRouteById(int id) {
        Session session = this.factory.getObject().getCurrentSession();
        TrainRoute t = this.findRouteById(id);
        session.remove(t);
    }

    @Override
    public void addOrUpdateRoute(TrainRoute rou) {
        Session s = this.factory.getObject().getCurrentSession();

        if (rou.getId() == null) {
            s.persist(rou);
        } else {
            s.merge(rou);
        }
    }

    @Override
    public List<TrainRoute> findLastRouteByTrainId(int trainId) {
        Session session = this.factory.getObject().getCurrentSession();
        Query<TrainRoute> q = session.createNamedQuery("TrainRoute.findLastByTrainId", TrainRoute.class);
        q.setParameter("trainId", trainId);
        q.setMaxResults(1);
        return q.getResultList();
    }

    @Override
    public void shiftStopOrdersUp(int trainId, int stopOrder) {
        Session session = factory.getObject().getCurrentSession();

        // Lấy danh sách route cần dẩy
        List<TrainRoute> routesToShift = session.createNamedQuery("TrainRoute.findRoutesToShift", TrainRoute.class)
                .setParameter("trainId", trainId)
                .setParameter("stopOrder", stopOrder)
                .getResultList();

        // Tăng stopOrder từng route
        for (TrainRoute r : routesToShift) {
            r.setStopOrder(r.getStopOrder() + 1);
            session.merge(r);
        }

        session.flush(); // Đẩy thay đổi lên DB
    }

}
