/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Service;
import com.kmt.pojo.Station;
import com.kmt.service.ServiceService;
import com.kmt.service.StationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author kieum
 */
@Controller
public class StationController {

    @Autowired
    private StationService staSer;
    
    @Autowired
    private ServiceService serSer;

    @GetMapping("/stations")
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

    @GetMapping("/stations/add")
    public String addStationForm(Model model) {
        model.addAttribute("station", new Station());
        return "addOrUpdateStation";
    }

    @GetMapping("/stations/add/{id}")
    public String updateStationForm(@PathVariable("id") int id, Model model) {
        Station station = staSer.getStationById(id);
        model.addAttribute("station", station);

        List<Service> services = serSer.getServicesByStationId(id);
        model.addAttribute("services", services);

        return "addOrUpdateStation";
    }

    @PostMapping("/stations/add/submit")
    public String addStation(@ModelAttribute Station station) {
        this.staSer.addOrUpdateStation(station);
        return "redirect:/stations";
    }

    @GetMapping("/stations/delete/{id}")
    public String deleteStation(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        staSer.deleteStationById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa ga tàu thành công!");

        return "redirect:/stations";
    }
}
