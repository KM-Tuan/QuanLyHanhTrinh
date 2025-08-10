/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.FoodCategory;
import com.kmt.repository.FoodCategoryRepository;
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
public class FoodCategoryRepositoryImpl implements FoodCategoryRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<FoodCategory> getCate() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("FoodCategory.findAll", FoodCategory.class);
        return q.getResultList();
    }

    @Override
    public FoodCategory getCategoryById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<FoodCategory> q = s.createNamedQuery("FoodCategory.findById", FoodCategory.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public void addOrUpdateCategory(FoodCategory fc) {
        Session s = this.factory.getObject().getCurrentSession();

        if (fc.getId() == null) {
            s.persist(fc);
        } else {
            s.merge(fc);
        }
    }

    @Override
    public void deleteCategoryById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        FoodCategory fc = this.getCategoryById(id);
        s.remove(fc);
    }

}
