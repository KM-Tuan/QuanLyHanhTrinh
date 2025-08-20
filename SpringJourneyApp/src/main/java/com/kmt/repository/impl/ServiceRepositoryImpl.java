/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Service;
import com.kmt.pojo.Station;
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

    @Override
    public Service getServiceById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Service> q = s.createNamedQuery("Service.findById", Service.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public void addOrUpdateService(Service ser) {
        Session s = this.factory.getObject().getCurrentSession();

        if (ser.getId() == null) {
            s.persist(ser);
        } else {
            s.merge(ser);
        }
    }

    @Override
    public void deleteServiceById(int id) {
        Session session = factory.getObject().getCurrentSession();

        // Lấy service
        Service service = this.getServiceById(id);

        // Nếu service liên kết với station
        if (service.getStationId() != null) {
            Station station = service.getStationId();

            // Loại bỏ service khỏi station.serviceSet
            station.getServiceSet().remove(service);
            session.saveOrUpdate(station); // cập nhật collection
        }

        // Xóa service
        session.remove(service);
    }

}
