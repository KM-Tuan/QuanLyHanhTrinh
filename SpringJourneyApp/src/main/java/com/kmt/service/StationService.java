/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.service;

import com.kmt.pojo.Station;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface StationService {
    List<Station> getStas();
    List<Station> getStationsPaginated(int page, int size);
    long countStations();
    Station getStationById(int id);
    void addOrUpdateStation(Station s);
    void deleteStationById(int id);
}
