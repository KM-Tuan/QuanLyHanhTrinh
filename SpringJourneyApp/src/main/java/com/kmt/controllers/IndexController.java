/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author kieum
 */
@Controller
public class IndexController {

    @Autowired
    private JourneyService jourSer;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("journeys", this.jourSer.getJoursCompleted());
        return "index";
    }
}
