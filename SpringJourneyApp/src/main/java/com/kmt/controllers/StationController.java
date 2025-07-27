/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Station;
import com.kmt.service.StationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author kieum
 */
@Controller
public class StationController {

    @Autowired
    private StationService staSer;

    @RequestMapping("/stations")
    public String index(Model model) {
        List<Station> allStations = this.staSer.getStas();
        int total = allStations.size();
        int mid = total / 2;

        List<Station> stations1 = allStations.subList(0, mid);
        List<Station> stations2 = allStations.subList(mid, total);

        model.addAttribute("staList1", stations1);
        model.addAttribute("staList2", stations2);

        return "stations"; // Tên template HTML
    }
}
