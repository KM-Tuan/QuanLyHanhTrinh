/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kmt.repository;

import com.kmt.pojo.Station;
import java.util.List;

/**
 *
 * @author kieum
 */
public interface StationRepository {
    List<Station> getStas();
    Station getStationById(int id);
    void addOrUpdateStation(Station station);
    void deleteStationById(int id);
}
