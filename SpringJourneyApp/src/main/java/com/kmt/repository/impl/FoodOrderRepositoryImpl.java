/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.FoodOrder;
import com.kmt.repository.FoodOrderRepository;
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
public class FoodOrderRepositoryImpl implements FoodOrderRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public FoodOrder saveOrder(FoodOrder order) {
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(order);
        return order;
    }
    
    @Override
    public List<Object[]> getTotalRevenueByDay() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Object[]> query = s.createNamedQuery("FoodOrder.totalRevenueByDay", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getTotalRevenueByMonth() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Object[]> query = s.createNamedQuery("FoodOrder.totalRevenueByMonth", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<Object[]> getTotalRevenueByYear() {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Object[]> query = s.createNamedQuery("FoodOrder.totalRevenueByYear", Object[].class);
        return query.getResultList();
    }

    @Override
    public List<FoodOrder> getOrderByUserId(int userId) {
        Session s = factory.getObject().getCurrentSession();
        Query<FoodOrder> q = s.createNamedQuery("FoodOrder.findByUserId", FoodOrder.class);
        q.setParameter("userId", userId);
        return q.getResultList();
    }

}
