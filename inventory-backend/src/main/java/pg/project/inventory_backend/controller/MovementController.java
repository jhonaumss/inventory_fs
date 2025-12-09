package pg.project.inventory_backend.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pg.project.inventory_backend.dto.MovementRequest;
import pg.project.inventory_backend.service.MovementService;

@RestController
@RequestMapping("/api/movements")
@CrossOrigin(origins = "https://localhost:5173")
public class MovementController {

    private final MovementService movementService;

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
    }

    @PreAuthorize("hasAnyRole('ROLE_SALES','ROLE_MANAGER')")
    @PostMapping
    public ResponseEntity<Void> registerMovement(
            @Valid @RequestBody MovementRequest request,
            Authentication auth
    ) {
        String username = auth.getName();
        boolean isManager = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"));
        movementService.registerMovements(request, username, isManager);
        return ResponseEntity.ok().build();
    }
}
