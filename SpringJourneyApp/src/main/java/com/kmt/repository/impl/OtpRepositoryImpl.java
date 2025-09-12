/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.Otp;
import com.kmt.repository.OtpRepository;
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
public class OtpRepositoryImpl implements OtpRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void addOtp(Otp o) {
        Session s = this.factory.getObject().getCurrentSession();

        if (o.getId() == null) {
            s.persist(o);
        } else {
            s.merge(o);
        }
    }

    @Override
    public Otp getOtpById(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        Query<Otp> q = s.createNamedQuery("Otp.findById", Otp.class);
        q.setParameter("id", id);
        return q.getSingleResult();
    }

    @Override
    public Otp getOtpByEmailId(int emailId) {
        Session s = this.factory.getObject().getCurrentSession();

        Query<Otp> q = s.createNamedQuery("Otp.findByEmaiId", Otp.class);
        q.setParameter("emailId", emailId);
        return q.getSingleResult();
    }

    @Override
    public void deleteOtpById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Otp o = this.getOtpById(id);
        s.remove(o);
    }
}
