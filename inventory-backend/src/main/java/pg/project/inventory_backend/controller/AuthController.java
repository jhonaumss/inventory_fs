package pg.project.inventory_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pg.project.inventory_backend.dto.AuthResponse;
import pg.project.inventory_backend.dto.LoginRequest;
import pg.project.inventory_backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "https://localhost:5173")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
