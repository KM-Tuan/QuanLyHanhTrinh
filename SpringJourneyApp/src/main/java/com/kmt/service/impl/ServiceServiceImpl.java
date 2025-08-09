/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.kmt.pojo.Service;
import com.kmt.pojo.Station;
import com.kmt.repository.ServiceRepository;
import com.kmt.repository.StationRepository;
import com.kmt.service.ServiceService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kieum
 */
@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serRepo;

    @Autowired
    private StationRepository staRepo;
    
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Service> getServicesByStationId(int stationId) {
        return this.serRepo.getServicesByStationId(stationId);
    }

    @Override
    public Service getServiceById(int id) {
        return this.serRepo.getServiceById(id);
    }

    @Override
    public void deleteServiceById(int id) {
        this.serRepo.deleteServiceById(id);
    }

    @Override
    public void addOrUpdateService(Service ser, int stationId) {
        // Lấy station và gán vào service
        Station station = staRepo.getStationById(stationId);
        ser.setStationId(station);

        Service targetService;

        if (ser.getId() != null) { // Update
            targetService = serRepo.getServiceById(ser.getId());
            targetService.setName(ser.getName());
            targetService.setDescription(ser.getDescription());
            targetService.setStationId(station);
        } else { // Thêm mới
            targetService = ser;
        }

        // Xử lý upload ảnh nếu có
        if (ser.getFile() != null && !ser.getFile().isEmpty()) {
            try {
                Map res = cloudinary.uploader().upload(ser.getFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                targetService.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ServiceServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        serRepo.addOrUpdateService(targetService);
    }

}
