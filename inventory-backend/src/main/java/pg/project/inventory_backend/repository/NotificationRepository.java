package pg.project.inventory_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pg.project.inventory_backend.model.Notification;
import pg.project.inventory_backend.model.NotificationType;
import pg.project.inventory_backend.model.Product;
import pg.project.inventory_backend.model.User;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    boolean existsByUserAndProductAndType(User user, Product product, NotificationType type);

    long countByUserAndReadFlagFalse(User user);

    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}
