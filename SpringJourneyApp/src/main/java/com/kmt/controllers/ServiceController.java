/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Service;
import com.kmt.pojo.Station;
import com.kmt.service.ServiceService;
import com.kmt.service.StationService;
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
public class ServiceController {

    @Autowired
    private ServiceService serSer;

    @Autowired
    private StationService staSer;

    @GetMapping("/services/add/{stationId}")
    public String addServiceForm(@PathVariable("stationId") int stationId, Model model) {
        Station station = staSer.getStationById(stationId);
        model.addAttribute("station", station);
        model.addAttribute("service", new Service());
        return "addOrUpdateService";
    }

    @GetMapping("/services/add/{id}/{stationId}")
    public String editServiceForm(@PathVariable("id") int id, @PathVariable("stationId") int stationId, Model model) {
        Service service = serSer.getServiceById(id);
        Station station = staSer.getStationById(stationId);

        model.addAttribute("service", service);
        model.addAttribute("station", station);
        return "addOrUpdateService";
    }

    @PostMapping("/services/add/submit")
    public String addService(@ModelAttribute Service service, @RequestParam("stationIdValue") int stationId) {
        serSer.addOrUpdateService(service, stationId);
        return "redirect:/stations/add/" + stationId;
    }

    @GetMapping("/services/delete/{id}/{stationId}")
    public String deleteService(@PathVariable("id") int id, @PathVariable("stationId") int stationId, RedirectAttributes redirectAttributes) {
        serSer.deleteServiceById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa dịch vụ thành công!");
        return "redirect:/stations/add/" + stationId;
    }
}
