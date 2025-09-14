package com.kmt.service.impl;

import com.kmt.pojo.Journey;
import com.kmt.pojo.TrainRoute;
import com.kmt.pojo.User;
import com.kmt.service.EmailService;
import com.kmt.service.JourneyService;
import com.kmt.service.TrainRouteService;
import com.kmt.service.UserService;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JourneyStatusScheduler {

    @Autowired
    private JourneyService journeySer;

    @Autowired
    private TrainRouteService trainRouteSer;

    @Autowired
    private UserService userSer;

    @Autowired
    private EmailService emailSer;

    // 1 phút thực = 1 giờ ảo (1 giây thực = 60 giây ảo)
    private static final int SPEED_MULTIPLIER = 60;

    // cache lưu lại những thông báo đã gửi: key = journeyId-routeId-userId
    private final Map<String, Boolean> notified = new ConcurrentHashMap<>();

    public int calculateProgress(Journey j, LocalDateTime now) {
        LocalDateTime dep = j.getDepartureTime();
        LocalDateTime arr = j.getArrivalTime();

        if (now.isBefore(dep)) {
            return 0;
        } else if (now.isAfter(arr)) {
            return 100;
        } else {
            long realSecondsElapsed = java.time.Duration.between(dep, now).getSeconds();
            long virtualSecondsElapsed = realSecondsElapsed * SPEED_MULTIPLIER;
            long totalVirtualSeconds = java.time.Duration.between(dep, arr).getSeconds();

            int progress = (int) ((virtualSecondsElapsed * 100) / totalVirtualSeconds);
            return Math.min(Math.max(progress, 0), 100);
        }
    }

    @Scheduled(fixedRate = 500)
    @Transactional
    public void updateJourneyStatuses() {
        List<Journey> journeys = journeySer.getAllJourneysNotCompleted();
        LocalDateTime now = LocalDateTime.now();

        for (Journey j : journeys) {
            int progress = calculateProgress(j, now);

            if (progress == 0) {
                j.setStatus(Journey.JourneyStatus.WAITTING);
            } else if (progress >= 100) {
                j.setStatus(Journey.JourneyStatus.COMPLETED);
                List<User> passengerUsers = userSer.getUsersByRole(User.UserRole.PASSENGER);
                String subject = "[THÔNG BÁO] - KẾT THÚC HÀNH TRÌNH";

                for (User passenger : passengerUsers) {
                    String key = j.getId() + "-COMPELETED-" + passenger.getId();

                    if (!notified.containsKey(key)) {
                        StringBuilder content = new StringBuilder();
                        content.append("Nhà ga xin thông báo hành khách ")
                                .append(passenger.getFirstName()).append(" ")
                                .append(passenger.getLastName())
                                .append(" đã kết thúc hành trình ")
                                .append(j.getName()).append(".\n")
                                .append("Xin cảm ơn hành khách đã lựa chọn hành trình của chúng tôi! Chúc hành khách có một ngày thật vui vẻ!\n");

                        emailSer.sendEmail(passenger.getEmail().getId(),
                                subject, content);

                        notified.put(key, true);
                    }
                }

                notified.keySet().removeIf(key -> key.startsWith(j.getId() + "-"));
            } else {
                j.setStatus(Journey.JourneyStatus.RUNNING);
            }

            double distanceTraveled = (j.getTotalDistance() * progress) / 100.0;
            TrainRoute nextRoute = trainRouteSer.findNextRouteByProgress(j.getTrainId().getId(), distanceTraveled, j.getDepartureStationId().getId(), j.getArrivalStationId().getId());

            if (nextRoute != null) {
                List<User> passengerUsers = userSer.getUsersByRole(User.UserRole.PASSENGER);

                long remainingSeconds = nextRoute.getTravelTime().toSecondOfDay();
                Duration duration = Duration.ofSeconds(remainingSeconds);
                long hours = duration.toHours();
                long minutes = duration.toMinutesPart();
                long seconds = duration.toSecondsPart();
                String etaFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds);

                String subject = "[THÔNG BÁO] - SẮP TỚI GA TÀU KẾ TIẾP";

                for (User passenger : passengerUsers) {
                    String key = j.getId() + "-" + nextRoute.getId() + "-" + passenger.getId();

                    if (!notified.containsKey(key)) {
                        StringBuilder content = new StringBuilder();
                        content.append("Nhà ga xin thông báo hành khách ")
                                .append(passenger.getFirstName()).append(" ")
                                .append(passenger.getLastName())
                                .append(" đang di chuyển trên hành trình ")
                                .append(j.getName()).append(".\n")
                                .append("Thời gian dự kiến còn khoảng ")
                                .append(etaFormatted).append(" nữa là sẽ đến ga ")
                                .append(nextRoute.getArrivalStationId().getName())
                                .append("\n");

                        emailSer.sendEmail(passenger.getEmail().getId(),
                                subject, content);

                        notified.put(key, true);
                    }
                }
            }

            journeySer.addOrUpdateJourney(j);
        }
    }
}
