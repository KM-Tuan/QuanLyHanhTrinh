/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Food;
import com.kmt.repository.FoodRepository;
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
public class FoodRepositoryImpl implements FoodRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    public List<Food> getFoods() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Food.findAll", Food.class);
        return q.getResultList();
    }
}
