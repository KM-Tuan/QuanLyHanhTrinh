/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.FoodOrder;
import com.kmt.repository.FoodOrderRepository;
import org.hibernate.Session;
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

}
