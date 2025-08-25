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

    @Override
    public void addOrUpdateFood(Food f) {
        Session s = this.factory.getObject().getCurrentSession();

        if (f.getId() == null) {
            s.persist(f);
        } else {
            s.merge(f);
        }
    }
    
    @Override
    public Food getFoodById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Food> q = s.createNamedQuery("Food.findById", Food.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    public List<Food> getFoods() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Food.findAll", Food.class);
        return q.getResultList();
    }
    
    @Override
    public List<Food> getFoodsPaginated(int page, int size) {
        Session session = factory.getObject().getCurrentSession();
        Query<Food> q = session.createNamedQuery("Food.findAllPaginated", Food.class);

        q.setFirstResult(page * size);
        q.setMaxResults(size);

        return q.getResultList();
    }

    @Override
    public long countFoods() {
        Session session = factory.getObject().getCurrentSession();
        Query<Long> q = session.createNamedQuery("Food.countAll", Long.class);
        return q.getSingleResult();
    }

    @Override
    public void deleteFoodById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Food f = this.getFoodById(id);
        s.remove(f);
    }
    
}
