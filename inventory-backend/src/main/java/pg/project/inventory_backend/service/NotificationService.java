package pg.project.inventory_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pg.project.inventory_backend.exceptions.BusinessException;
import pg.project.inventory_backend.model.*;
import pg.project.inventory_backend.repository.NotificationRepository;
import pg.project.inventory_backend.repository.ProductRepository;
import pg.project.inventory_backend.repository.RoleRepository;
import pg.project.inventory_backend.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private static final int WARNING_DAYS = 7;
    private final RoleRepository roleRepository;

    // =========================
    // 1) Generar notificaciones
    // =========================
    @Transactional
    public void generateExpiryNotifications() {
        LocalDate today = LocalDate.now();
        LocalDate warningLimit = today.plusDays(WARNING_DAYS);

        // Productos que vencen hasta el límite de warning
        List<Product> candidates = productRepository.findByDueDateLessThanEqual(warningLimit);
        if (candidates.isEmpty()) return;

        // Usuarios objetivo: MANAGER + SALES
        List<User> recipients = userRepository.findByRoleIn(
                List.of(roleRepository.findByName(
                        "ROLE_MANAGER").get(), roleRepository.findByName("ROLE_SALES").get())
        );
        if (recipients.isEmpty()) return;

        LocalDateTime now = LocalDateTime.now();

        for (Product product : candidates) {
            LocalDate due = product.getDueDate();
            if (due == null) continue;

            NotificationType type = classifyExpiry(today, warningLimit, due);
            if (type == null) continue; // fuera de rango

            String title;
            String message;

            if (type == NotificationType.CRITICAL) {
                title = "Producto vencido";
                if (due.isEqual(today)) {
                    message = "El producto \"" + product.getName() + "\" se vence hoy (" + due + ").";
                } else {
                    message = "El producto \"" + product.getName() + "\" está vencido desde " + due + ".";
                }
            } else {
                title = "Producto próximo a vencer";
                message = "El producto \"" + product.getName() + "\" se vencerá el " + due + ".";
            }

            for (User user : recipients) {
                boolean exists = notificationRepository.existsByUserAndProductAndType(user, product, type);
                if (exists) continue;

                Notification notif = new Notification();
                notif.setUser(user);
                notif.setProduct(product);
                notif.setType(type);
                notif.setTitle(title);
                notif.setMessage(message);
                notif.setReadFlag(false);
                notif.setCreatedAt(now);

                notificationRepository.save(notif);
            }
        }
    }

    private NotificationType classifyExpiry(LocalDate today, LocalDate warningLimit, LocalDate dueDate) {
        // CRITICAL: dueDate <= today
        if (!dueDate.isAfter(today)) {
            return NotificationType.CRITICAL;
        }
        // WARNING: hoy < dueDate <= warningLimit
        if (!dueDate.isAfter(warningLimit)) {
            return NotificationType.WARNING;
        }
        // Más allá del límite → sin notificación
        return null;
    }

    // =====================================
    // 2) Operaciones para el usuario actual
    // =====================================

    public List<Notification> getNotificationsForUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public long countUnread(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));
        return notificationRepository.countByUserAndReadFlagFalse(user);
    }

    @Transactional
    public void markAllAsRead(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("Usuario no encontrado"));

        List<Notification> list = notificationRepository.findByUserOrderByCreatedAtDesc(user);
        list.forEach(n -> n.setReadFlag(true));
        // al estar en @Transactional, se sincroniza solo
    }

    @Transactional
    public void deleteNotification(UUID userId, UUID notificationId) {
        Notification n = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new BusinessException("Notificación no encontrada"));

        if (!n.getUser().getId().equals(userId)) {
            throw new BusinessException("No puedes eliminar esta notificación");
        }

        notificationRepository.delete(n);
    }
}
