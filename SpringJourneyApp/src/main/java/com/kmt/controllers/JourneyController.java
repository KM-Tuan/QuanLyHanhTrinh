/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Journey;
import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import com.kmt.service.JourneyService;
import com.kmt.service.StationService;
import com.kmt.service.TrainRouteService;
import com.kmt.service.TrainService;
import com.kmt.service.UserService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private TrainRouteService trainRouteSer;

    @Autowired
    private StationService staSer;

    @Autowired
    private UserService userService;

    @GetMapping("/journeys")
    public String index(Model model) {
        model.addAttribute("journeys", this.jourSer.getJours());
        return "journeys";
    }

    @GetMapping("/journeys/add/step1")
    public String step1(Model model) {
        model.addAttribute("stations", staSer.getStas());
        return "addJourneyStep1";
    }

    // Bước 2: nhận ga đi và ga đến, hiển thị chọn tàu và nhập thời gian khởi hành
    @PostMapping("/journeys/add/step2")
    public String step2(@RequestParam("departureStationId") int departureId, @RequestParam("arrivalStationId") int arrivalId, Model model) {
        model.addAttribute("departureStationId", departureId);
        model.addAttribute("arrivalStationId", arrivalId);
        model.addAttribute("trains", trainRouteSer.findTrainsByStations(departureId, arrivalId));
        return "addJourneyStep2";
    }

    // Bước 3: tính tổng quãng đường, tổng thời gian, arrivalTime, hiển thị form nhập các thông tin còn lại
    @PostMapping("/journeys/add/step3")
    public String step3(@RequestParam("departureStationId") int departureId, @RequestParam("arrivalStationId") int arrivalId, @RequestParam("trainId") int trainId, @RequestParam("departureTime") String departureTimeStr, Model model) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr, formatter);

        List<TrainRoute> routes = trainRouteSer.findByTrainAndStations(trainId, departureId, arrivalId);
        if (routes == null || routes.isEmpty()) {
            model.addAttribute("error", "Không tìm thấy tuyến phù hợp!");
            return "addJourneyStep2";
        }

        int totalDistance = 0;
        Duration totalTravelDuration = Duration.ZERO;

        for (TrainRoute tr : routes) {
            totalDistance += tr.getDistance();
            LocalTime t = tr.getTravelTime();
            totalTravelDuration = totalTravelDuration
                    .plusHours(t.getHour())
                    .plusMinutes(t.getMinute())
                    .plusSeconds(t.getSecond());
        }

        long totalSeconds = totalTravelDuration.getSeconds();
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long secs = totalSeconds % 60;

        LocalDateTime arrivalTime = departureTime.plus(totalTravelDuration);
        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, secs);

        model.addAttribute("departureStationId", departureId);
        model.addAttribute("arrivalStationId", arrivalId);
        model.addAttribute("trainId", trainId);
        model.addAttribute("departureTime", departureTimeStr);
        model.addAttribute("arrivalTime", arrivalTime.format(formatter));
        model.addAttribute("totalDistance", totalDistance);
        model.addAttribute("totalTravelTime", formattedTime);  // Dùng String cho hiển thị
        model.addAttribute("statuses", Journey.JourneyStatus.values());

        return "addJourneyStep3";
    }

    // Bước cuối: nhận dữ liệu và lưu
    @PostMapping("/journeys/add/submit")
    public String submitJourney(@RequestParam("departureStationId") int departureId,
            @RequestParam("arrivalStationId") int arrivalId,
            @RequestParam("trainId") int trainId,
            @RequestParam("departureTime") String departureTimeStr,
            @RequestParam("arrivalTime") String arrivalTimeStr,
            @RequestParam("totalDistance") int totalDistance,
            @RequestParam("totalTravelTime") String totalTravelTimeStr,
            @RequestParam("status") String statusStr,
            RedirectAttributes redirectAttributes) {

        Journey journey = new Journey();
        journey.setDepartureStationId(staSer.getStationById(departureId));
        journey.setArrivalStationId(staSer.getStationById(arrivalId));
        journey.setTrainId(trainSer.getTrainById(trainId));
        journey.setDepartureTime(LocalDateTime.parse(departureTimeStr));
        journey.setArrivalTime(LocalDateTime.parse(arrivalTimeStr));
        journey.setTotalDistance(totalDistance);
        journey.setTotalTravelTime(totalTravelTimeStr);
        journey.setStatus(Journey.JourneyStatus.valueOf(statusStr));
        journey.setName(jourSer.generateRandomName());
        journey.setCreatedAt(LocalDateTime.now());
        journey.setCreatedBy(userService.getCurrentUser());

        jourSer.saveJourney(journey);

        redirectAttributes.addFlashAttribute("success", "Thêm hành trình thành công!");
        return "redirect:/journeys";
    }
}