package com.kmt.service.impl;

import com.kmt.pojo.Journey;
import com.kmt.pojo.NotificationSubscription;
import com.kmt.pojo.TrainRoute;
import com.kmt.pojo.User;
import com.kmt.service.EmailService;
import com.kmt.service.JourneyService;
import com.kmt.service.NotificationSubscriptionService;
import com.kmt.service.TrainRouteService;
import com.kmt.service.UserService;
import java.time.Duration;
import java.time.LocalDateTime;
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

    @Autowired
    private NotificationSubscriptionService notiSer;

    private final Map<String, Boolean> notified = new ConcurrentHashMap<>();
    private static final int SPEED_MULTIPLIER = 60;

    public int calculateProgress(Journey j, LocalDateTime now) {
        LocalDateTime dep = j.getDepartureTime();
        LocalDateTime arr = j.getArrivalTime();

        if (now.isBefore(dep)) return 0;
        if (now.isAfter(arr)) return 100;

        long realSecondsElapsed = Duration.between(dep, now).getSeconds();
        long virtualSecondsElapsed = realSecondsElapsed * SPEED_MULTIPLIER;
        long totalVirtualSeconds = Duration.between(dep, arr).getSeconds();

        return (int) Math.min(Math.max((virtualSecondsElapsed * 100) / totalVirtualSeconds, 0), 100);
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
            } else {
                j.setStatus(Journey.JourneyStatus.RUNNING);
            }

            // Tính khoảng cách và ga tiếp theo
            double distanceTraveled = (j.getTotalDistance() * progress) / 100.0;
            TrainRoute nextRoute = trainRouteSer.findNextRouteByProgress(
                    j.getTrainId().getId(),
                    distanceTraveled,
                    j.getDepartureStationId().getId(),
                    j.getArrivalStationId().getId()
            );

            // Lấy danh sách user đã đăng ký nhận thông báo
            List<NotificationSubscription> subs = notiSer.findByJourney(j.getName());

            for (NotificationSubscription sub : subs) {
                User passenger = sub.getUserId();
                String key;

                if (progress >= 100) {
                    key = j.getId() + "-COMPLETED-" + passenger.getId();
                    if (!notified.containsKey(key)) {
                        sendCompletedMail(j, passenger);
                        notified.put(key, true);
                    }
                } else if (nextRoute != null) {
                    key = j.getId() + "-" + nextRoute.getId() + "-" + passenger.getId();
                    if (!notified.containsKey(key)) {
                        sendNextStationMail(j, passenger, nextRoute);
                        notified.put(key, true);
                    }
                }
            }

            journeySer.addOrUpdateJourney(j);
        }
    }

    private void sendCompletedMail(Journey j, User passenger) {
        String subject = "[THÔNG BÁO] - KẾT THÚC HÀNH TRÌNH";
        StringBuilder content = new StringBuilder();
        content.append("Nhà ga xin thông báo hành khách ")
                .append(passenger.getFirstName()).append(" ").append(passenger.getLastName())
                .append(" đã kết thúc hành trình ").append(j.getName())
                .append("\nXin cảm ơn hành khách!");
        emailSer.sendEmail(passenger.getEmail().getId(), subject, content);
    }

    private void sendNextStationMail(Journey j, User passenger, TrainRoute nextRoute) {
        long remainingSeconds = nextRoute.getTravelTime().toSecondOfDay();
        Duration duration = Duration.ofSeconds(remainingSeconds);
        String etaFormatted = String.format("%02d:%02d:%02d",
                duration.toHours(),
                duration.toMinutesPart(),
                duration.toSecondsPart());

        String subject = "[THÔNG BÁO] - SẮP TỚI GA KẾ TIẾP";
        StringBuilder content = new StringBuilder();
        content.append("Nhà ga xin thông báo hành khách ")
                .append(passenger.getFirstName()).append(" ").append(passenger.getLastName())
                .append(" đang di chuyển trên hành trình ").append(j.getName())
                .append("Thời gian dự kiến còn khoảng ").append(etaFormatted)
                .append(" nữa là sẽ đến ga ").append(nextRoute.getArrivalStationId().getName());
        emailSer.sendEmail(passenger.getEmail().getId(), subject, content);
    }
}

