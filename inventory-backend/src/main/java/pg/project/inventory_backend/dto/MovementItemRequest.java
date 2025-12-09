package pg.project.inventory_backend.dto;

import pg.project.inventory_backend.model.MovementType;

public record MovementItemRequest(
        String productId,
        int quantity,
        MovementType type
) {}

