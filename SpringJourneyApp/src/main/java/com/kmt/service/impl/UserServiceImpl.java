/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Passenger;
import com.kmt.pojo.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.kmt.repository.UserRepository;
import com.kmt.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getUsers() {
        return this.userRepo.getUsers();
    }
}
