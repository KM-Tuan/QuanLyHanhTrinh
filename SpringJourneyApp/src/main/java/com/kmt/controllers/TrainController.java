/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import com.kmt.service.TrainRouteService;
import com.kmt.service.TrainService;
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
public class TrainController {

    @Autowired
    private TrainService trainSer;
    
    @Autowired
    private TrainRouteService routeSer;
    
    @GetMapping("/trains")
    public String listTrains(Model model,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        List<Train> trains = trainSer.getTrainsPaginated(page, size);
        long totalTrains = trainSer.countTrains();
        int totalPages = (int) Math.ceil((double) totalTrains / size);

        model.addAttribute("trains", trains);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);

        return "trains";
    }
    
    @GetMapping("/trains/add")
    public String addTrainForm(Model model) {
        model.addAttribute("train", new Train());
        return "addOrUpdateTrain";
    }
    
    @GetMapping("/trains/add/{id}")
    public String updateTrain(@PathVariable("id") int id, Model model) {
        Train train = trainSer.getTrainById(id);
        model.addAttribute("train", train);
        
        List<TrainRoute> routes = routeSer.findRoutesByTrainId(id);
        model.addAttribute("routes", routes);
        
        return "addOrUpdateTrain";
    }
    
    @PostMapping("/trains/add/submit")
    public String addTrain(@ModelAttribute Train train) {
        this.trainSer.addOrUpdateTrain(train);
        return "redirect:/trains";
    }
    
    @GetMapping("/trains/delete/{id}")
    public String deleteTrain(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        trainSer.deleteTrainById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa tàu hỏa thành công!");
        
        return "redirect:/trains";
    } 
}
