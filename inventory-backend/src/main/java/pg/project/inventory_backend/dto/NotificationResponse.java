// pg/project/inventory_backend/dto/NotificationResponse.java
package pg.project.inventory_backend.dto;

import pg.project.inventory_backend.model.Notification;
import pg.project.inventory_backend.model.NotificationType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NotificationResponse(
        UUID id,
        String title,
        String message,
        NotificationType type,
        boolean read,
        LocalDateTime createdAt
) {
    public static NotificationResponse fromEntity(Notification n) {
        return new NotificationResponse(
                n.getId(),
                n.getTitle(),
                n.getMessage(),
                n.getType(),
                n.isReadFlag(),
                n.getCreatedAt()
        );
    }
}
