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
import org.springframework.web.bind.annotation.RequestParam;
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
    public String listStations(Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        List<Station> stations = staSer.getStationsPaginated(page, size);
        long totalStations = staSer.countStations();
        int totalPages = (int) Math.ceil((double) totalStations / size);

        model.addAttribute("stations", stations);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);

        return "stations";
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
