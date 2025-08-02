/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.kmt.pojo.Station;
import com.kmt.repository.StationRepository;
import com.kmt.service.StationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class StationServiceImpl implements StationService{
    
    @Autowired
    private StationRepository staRepo;
    
    @Override
    public List<Station> getStas() {
        return this.staRepo.getStas();
    }

    @Override
    public Station getStationById(int id) {
        return this.staRepo.getStationById(id);
    }
    
}
