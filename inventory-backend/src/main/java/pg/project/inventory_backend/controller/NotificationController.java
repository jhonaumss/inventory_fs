// pg/project/inventory_backend/controller/NotificationController.java
package pg.project.inventory_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pg.project.inventory_backend.dto.NotificationResponse;
import pg.project.inventory_backend.model.User;
import pg.project.inventory_backend.repository.UserRepository;
import pg.project.inventory_backend.service.NotificationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "https://localhost:5173")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public NotificationController(NotificationService notificationService,
                                  UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    private UUID currentUserId(Authentication auth) {
        String username = auth.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        return user.getId();
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getMyNotifications(Authentication auth) {
        UUID userId = currentUserId(auth);
        var notifs = notificationService.getNotificationsForUser(userId)
                .stream()
                .map(NotificationResponse::fromEntity)
                .toList();
        return ResponseEntity.ok(notifs);
    }

    @GetMapping("/unread-count")
    public ResponseEntity<Long> getUnreadCount(Authentication auth) {
        UUID userId = currentUserId(auth);
        long count = notificationService.countUnread(userId);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/mark-all-read")
    public ResponseEntity<Void> markAllRead(Authentication auth) {
        UUID userId = currentUserId(auth);
        notificationService.markAllAsRead(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable UUID id, Authentication auth) {
        UUID userId = currentUserId(auth);
        notificationService.deleteNotification(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/generate-notifications")
    public ResponseEntity<Void> generate() {
        notificationService.generateExpiryNotifications();
        return ResponseEntity.ok().build();
    }
}
