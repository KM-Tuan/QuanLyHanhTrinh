/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.repository.impl;

import com.kmt.pojo.User;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.kmt.repository.UserRepository;

/**
 *
 * @author kieum
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<User> getUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findAll", User.class);
        return q.getResultList();
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername", User.class);
        q.setParameter("username", username);
        return (User) q.getSingleResult();
    }
    
    @Override
    public User getUserById(int id) {
    Session s = this.factory.getObject().getCurrentSession();
    Query<User> q = s.createNamedQuery("User.findById", User.class);
    q.setParameter("id", id);
    return q.getSingleResult();
}

    @Override
    public void addOrUpdateUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();

        if (user.getId() == null) {
            s.persist(user);
        } else {
            s.merge(user);
        }
    }

    @Override
    public void deleteUserById(int id) {
        Session s = factory.getObject().getCurrentSession();
        User u = this.getUserById(id);
        s.remove(u);
    }

}
