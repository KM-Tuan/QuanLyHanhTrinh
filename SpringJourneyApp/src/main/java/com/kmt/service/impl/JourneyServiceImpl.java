/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Journey;
import com.kmt.repository.JourneyRepository;
import com.kmt.service.JourneyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author kieum
 */
@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    private JourneyRepository jourRepo;

    @Override
    public List<Journey> getJours() {
        return this.jourRepo.getJours();
    }

}
