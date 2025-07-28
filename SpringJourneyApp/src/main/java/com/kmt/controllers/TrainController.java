/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Train;
import com.kmt.service.TrainService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author kieum
 */
@Controller
public class TrainController {

    @Autowired
    private TrainService trainSer;
    
    @GetMapping("/trains")
    public String index(Model model) {
        List<Train> allTrains = this.trainSer.getTrains();
        int total = allTrains.size();
        int mid = total / 2;

        List<Train> trains1 = allTrains.subList(0, mid);
        List<Train> trains2 = allTrains.subList(mid, total);

        model.addAttribute("trainList1", trains1);
        model.addAttribute("trainList2", trains2);

        return "trains"; // Tên template HTML
    }
}
