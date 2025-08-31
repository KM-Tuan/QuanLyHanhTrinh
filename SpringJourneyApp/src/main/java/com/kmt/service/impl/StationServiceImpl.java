/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kmt.pojo.Station;
import com.kmt.repository.StationRepository;
import com.kmt.service.StationService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@Service
@Transactional
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository staRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Station> getStas() {
        return this.staRepo.getStas();
    }

    @Override
    public List<Station> getStationsPaginated(int page, int size) {
        return this.staRepo.getStationsPaginated(page, size);
    }

    @Override
    public long countStations() {
        return this.staRepo.countStations();
    }

    @Override
    public Station getStationById(int id) {
        return this.staRepo.getStationById(id);
    }

    @Override
    public void addOrUpdateStation(Station s) {
        Station targetStation = (s.getId() != null) ? staRepo.getStationById(s.getId()) : s;

        // Nếu update thì set lại các trường cần thay đổi
        if (s.getId() != null && targetStation != null) {
            targetStation.setName(s.getName());
        }

        // Upload ảnh nếu có file
        if (s.getFile() != null && !s.getFile().isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(s.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                targetStation.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(StationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        staRepo.addOrUpdateStation(targetStation);
    }

    @Override
    public void deleteStationById(int id) {
        this.staRepo.deleteStationById(id);
    }
}
