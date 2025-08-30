/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kmt.controllers;

import com.kmt.pojo.Station;
import com.kmt.pojo.Train;
import com.kmt.pojo.TrainRoute;
import com.kmt.service.StationService;
import com.kmt.service.TrainRouteService;
import com.kmt.service.TrainService;
import java.time.LocalTime;
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
public class TrainRouteController {

    @Autowired
    private TrainRouteService routeSer;

    @Autowired
    private TrainService trainSer;

    @Autowired
    private StationService staSer;

    @GetMapping("/train-routes/add/{trainId}")
    public String addTrainRouteForm(@PathVariable("trainId") int trainId, Model model) {
        Train train = trainSer.getTrainById(trainId);
        model.addAttribute("train", train);
        model.addAttribute("stations", staSer.getStas());

        TrainRoute lastRoute = routeSer.getLastRouteByTrainId(trainId);

        TrainRoute route = new TrainRoute();
        if (lastRoute == null) {
            route.setStopOrder(1); // Route đầu tiên
        } else {
            route.setStopOrder(lastRoute.getStopOrder() + 1); // Route tiếp theo
            model.addAttribute("lastArrivalStation", lastRoute.getArrivalStationId());
        }

        model.addAttribute("route", route);
        return "addOrUpdateTrainRoute";
    }

    @PostMapping("/train-routes/add/submit")
    public String addTrainRoute(
            @RequestParam(value = "routeId", required = false) Integer routeId,
            @RequestParam("trainIdValue") int trainId,
            @RequestParam("departureStationId") int depId,
            @RequestParam("arrivalStationId") int arrId,
            @RequestParam("distance") int distance,
            @RequestParam("travelTime") LocalTime travelTime,
            @RequestParam("stopOrder") int stopOrder) {

        // Lấy route đầu tiên của train
        TrainRoute firstRoute = routeSer.getRouteByTrainIdAndStopOrder(trainId, 1);

        // Lấy route cuối cùng của train
        TrainRoute lastRoute = routeSer.getLastRouteByTrainId(trainId);

        if (stopOrder == 1 && firstRoute != null) {
            // Route đầu tiên đã tồn tại => giữ nguyên ga đi
            depId = firstRoute.getDepartureStationId().getId();
        } else if (stopOrder != 1 && lastRoute != null) {
            // Route thứ 2 trở đi => ga đi = ga đến của route cuối cùng
            depId = lastRoute.getArrivalStationId().getId();
        }

        // Gọi service thêm hoặc cập nhật route
        routeSer.addOrUpdateRoute(routeId, trainId, depId, arrId, distance, travelTime, stopOrder);

        return "redirect:/trains/add/" + trainId;
    }

    @GetMapping("/train-routes/delete/{id}/{trainId}")
    public String deleteRoute(@PathVariable("id") int id, @PathVariable("trainId") int trainId, RedirectAttributes redirectAttributes) {
        routeSer.deleteRouteById(id, trainId);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa tuyến thành công!");

        return "redirect:/trains/add/" + trainId;
    }

}
