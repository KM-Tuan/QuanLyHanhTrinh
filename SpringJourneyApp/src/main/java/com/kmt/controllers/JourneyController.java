/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Journey;
import com.kmt.pojo.User;
import com.kmt.service.JourneyService;
import com.kmt.service.StationService;
import com.kmt.service.TrainService;
import com.kmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author kieum
 */
@Controller
public class JourneyController {

    @Autowired
    private JourneyService jourSer;

    @Autowired
    private TrainService trainSer;

    @Autowired
    private StationService staSer;

    @Autowired
    private UserService userService;

    @GetMapping("/journeys")
    public String index(Model model) {
        model.addAttribute("journeys", this.jourSer.getJours());
        return "journeys";
    }

    @GetMapping("/journeys/add")
    public String addJourneyForm(Model model) {
        model.addAttribute("journey", new Journey());

        model.addAttribute("trains", trainSer.getTrains());
        model.addAttribute("stations", staSer.getStas());

        return "addJourney";
    }

    @PostMapping("/journeys/add")
    public String addJourneySubmit(@ModelAttribute Journey journey, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Nếu có lỗi validation, trả lại form với dữ liệu và danh sách cần thiết
            System.out.println("Validation errors: " + result.getAllErrors());
            return "addJourney";
        }
        // Tạo tên ngẫu nhiên theo định dạng
        String generatedName = this.jourSer.generateRandomName();
        journey.setName(generatedName);

        // Có thể set thêm các trường khác nếu cần (ví dụ createdAt)
        journey.setCreatedAt(java.time.LocalDateTime.now());

        // Gán người tạo
        User currentUser = userService.getCurrentUser();
        journey.setCreatedBy(currentUser);

        // Lưu vào DB
        this.jourSer.saveJourney(journey);

        redirectAttributes.addFlashAttribute("success", "Thêm hành trình thành công với tên: " + generatedName);
        return "redirect:/journeys";
    }

}
