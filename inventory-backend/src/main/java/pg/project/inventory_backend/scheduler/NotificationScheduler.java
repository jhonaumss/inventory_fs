// pg/project/inventory_backend/scheduler/NotificationScheduler.java
package pg.project.inventory_backend.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pg.project.inventory_backend.service.NotificationService;

@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final NotificationService notificationService;

    // Todos los días a las 08:00
    @Scheduled(cron = "0 0 8 * * *")
    public void scheduleExpiryNotifications() {
        notificationService.generateExpiryNotifications();
    }
}
