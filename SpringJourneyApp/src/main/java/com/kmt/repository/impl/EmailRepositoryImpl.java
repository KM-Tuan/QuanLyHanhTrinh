/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Email;
import com.kmt.repository.EmailRepository;
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
public class EmailRepositoryImpl implements EmailRepository {
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Email getEmailById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query<Email> q = s.createNamedQuery("Email.findById", Email.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }
    
}
