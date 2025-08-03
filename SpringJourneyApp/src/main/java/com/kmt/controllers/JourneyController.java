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
import org.springframework.web.bind.annotation.PathVariable;
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
    public String addStep1(Model model) {
        model.addAttribute("stations", staSer.getStas());
        return "addJourneyStep1";
    }

    @GetMapping("/journeys/add/{id}/step1")
    public String updateStep1(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Journey journey = jourSer.getJourneyById(id);

        model.addAttribute("stations", staSer.getStas());
        model.addAttribute("journey", journey);
        model.addAttribute("id", id);

        return "addJourneyStep1";
    }

    // Bước 2: nhận ga đi và ga đến, hiển thị chọn tàu và nhập thời gian khởi hành
    @PostMapping("/journeys/add/step2")
    public String addStep2(@RequestParam("departureStationId") int departureId, @RequestParam("arrivalStationId") int arrivalId, Model model) {
        model.addAttribute("departureStationId", departureId);
        model.addAttribute("arrivalStationId", arrivalId);
        model.addAttribute("trains", trainRouteSer.findTrainsByStations(departureId, arrivalId));
        return "addJourneyStep2";
    }

    @PostMapping("/journeys/add/{id}/step2")
    public String updateStep2(@PathVariable("id") int id,
            @RequestParam("departureStationId") int departureId,
            @RequestParam("arrivalStationId") int arrivalId,
            Model model) {

        Journey journey = jourSer.getJourneyById(id);

        model.addAttribute("journey", journey);
        model.addAttribute("departureStationId", departureId);
        model.addAttribute("arrivalStationId", arrivalId);
        model.addAttribute("trains", trainRouteSer.findTrainsByStations(departureId, arrivalId));
        model.addAttribute("id", id);

        return "addJourneyStep2";
    }

    // Bước 3: tính tổng quãng đường, tổng thời gian, arrivalTime, hiển thị form nhập các thông tin còn lại
    @PostMapping("/journeys/add/step3")
    public String addStep3(@RequestParam("departureStationId") int departureId, @RequestParam("arrivalStationId") int arrivalId, @RequestParam("trainId") int trainId, @RequestParam("departureTime") String departureTimeStr, Model model) {

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

        return "addJourneyStep3";
    }

    @PostMapping("/journeys/add/{id}/step3")
    public String updateStep3(@PathVariable("id") int id,
            @RequestParam("departureStationId") int departureId,
            @RequestParam("arrivalStationId") int arrivalId,
            @RequestParam("trainId") int trainId,
            @RequestParam("departureTime") String departureTimeStr,
            Model model) {
        Journey journey = jourSer.getJourneyById(id);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr, formatter);

        List<TrainRoute> routes = trainRouteSer.findByTrainAndStations(trainId, departureId, arrivalId);
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
        LocalDateTime arrivalTime = departureTime.plus(totalTravelDuration);

        model.addAttribute("journey", journey);
        model.addAttribute("departureStationId", departureId);
        model.addAttribute("arrivalStationId", arrivalId);
        model.addAttribute("trainId", trainId);
        model.addAttribute("departureTime", departureTimeStr);
        model.addAttribute("arrivalTime", arrivalTime.format(formatter));
        model.addAttribute("totalDistance", totalDistance);
        model.addAttribute("totalTravelTime", String.format("%02d:%02d:%02d",
                totalTravelDuration.toHours(),
                totalTravelDuration.toMinutesPart(),
                totalTravelDuration.toSecondsPart()));
        model.addAttribute("id", id);

        return "addJourneyStep3";
    }

    // Bước cuối: nhận dữ liệu và lưu
    @PostMapping("/journeys/add/submit")
    public String submitAddJourney(@RequestParam("departureStationId") int departureId,
            @RequestParam("arrivalStationId") int arrivalId,
            @RequestParam("trainId") int trainId,
            @RequestParam("departureTime") String departureTimeStr,
            @RequestParam("arrivalTime") String arrivalTimeStr,
            @RequestParam("totalDistance") int totalDistance,
            @RequestParam("totalTravelTime") String totalTravelTimeStr,
            RedirectAttributes redirectAttributes) {

        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr);
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeStr);
        LocalDateTime now = LocalDateTime.now();

        Journey journey = new Journey();
        journey.setDepartureStationId(staSer.getStationById(departureId));
        journey.setArrivalStationId(staSer.getStationById(arrivalId));
        journey.setTrainId(trainSer.getTrainById(trainId));
        journey.setDepartureTime(departureTime);
        journey.setArrivalTime(arrivalTime);
        journey.setTotalDistance(totalDistance);
        journey.setTotalTravelTime(totalTravelTimeStr);
        journey.setName(jourSer.generateRandomName());
        journey.setCreatedAt(now);
        journey.setCreatedBy(userService.getCurrentUser());

        if (now.isBefore(departureTime)) {
            journey.setStatus(Journey.JourneyStatus.WAITTING);
        } else if (now.isAfter(arrivalTime)) {
            journey.setStatus(Journey.JourneyStatus.COMPLETED);
        } else {
            journey.setStatus(Journey.JourneyStatus.RUNNING);
        }

        jourSer.addOrUpdateJourney(journey);

        redirectAttributes.addFlashAttribute("success", "Thêm hành trình thành công!");
        return "redirect:/journeys";
    }

    @PostMapping("/journeys/add/{id}/submit")
    public String submitUpdateJourney(@PathVariable("id") int id,
            @RequestParam("departureStationId") int departureId,
            @RequestParam("arrivalStationId") int arrivalId,
            @RequestParam("trainId") int trainId,
            @RequestParam("departureTime") String departureTimeStr,
            @RequestParam("arrivalTime") String arrivalTimeStr,
            @RequestParam("totalDistance") int totalDistance,
            @RequestParam("totalTravelTime") String totalTravelTimeStr,
            RedirectAttributes redirectAttributes) {

        Journey journey = jourSer.getJourneyById(id);
        if (journey == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy hành trình để cập nhật!");
            return "redirect:/journeys";
        }

        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr);
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeStr);

        journey.setDepartureStationId(staSer.getStationById(departureId));
        journey.setArrivalStationId(staSer.getStationById(arrivalId));
        journey.setTrainId(trainSer.getTrainById(trainId));
        journey.setDepartureTime(departureTime);
        journey.setArrivalTime(arrivalTime);
        journey.setTotalDistance(totalDistance);
        journey.setTotalTravelTime(totalTravelTimeStr);

        jourSer.addOrUpdateJourney(journey);

        redirectAttributes.addFlashAttribute("success", "Cập nhật hành trình thành công!");
        return "redirect:/journeys";
    }
}
