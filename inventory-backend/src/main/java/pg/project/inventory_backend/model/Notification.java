package pg.project.inventory_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification
{
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private User user;              // dueño de la notificación

    private String title;           // "Alerta de caducidad"
    @Column(length = 500)
    private String message;         // "El producto Leche vence hoy"

    @Enumerated(EnumType.STRING)
    private NotificationType type;  // WARNING, CRITICAL, INFO

    private boolean readFlag = false;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private Product product;
}
