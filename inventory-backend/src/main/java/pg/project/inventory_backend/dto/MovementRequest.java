package pg.project.inventory_backend.dto;

import java.util.List;

public record MovementRequest (
        List<MovementItemRequest> items
){}
