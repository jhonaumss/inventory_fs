package pg.project.inventory_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pg.project.inventory_backend.model.InventoryMovement;

import java.util.UUID;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, UUID> {
}
