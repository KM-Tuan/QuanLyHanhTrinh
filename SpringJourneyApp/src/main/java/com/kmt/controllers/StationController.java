/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.service.StationService;
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
        model.addAttribute("stations", this.staSer.getStas());
        return "stations";
    }

}
