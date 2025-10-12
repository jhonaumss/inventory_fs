package pg.project.inventory_backend.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pg.project.inventory_backend.dto.AuthResponse;
import pg.project.inventory_backend.dto.LoginRequest;
import pg.project.inventory_backend.dto.RegisterRequest;
import pg.project.inventory_backend.model.Role;
import pg.project.inventory_backend.model.User;
import pg.project.inventory_backend.repository.RoleRepository;
import pg.project.inventory_backend.repository.UserRepository;
import pg.project.inventory_backend.security.JwtService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authManager,
                       JwtService jwt) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
        this.jwt = jwt;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already taken");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );

        // Default role = ROLE_USER
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        String token = jwt.generate(user.getUsername(), List.of(userRole.getName()));
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        // principal is org.springframework.security.core.userdetails.User
        var principal = (org.springframework.security.core.userdetails.User) auth.getPrincipal();
        var roles = principal.getAuthorities().stream().map(a -> a.getAuthority()).toList();

        String token = jwt.generate(principal.getUsername(), roles);
        return new AuthResponse(token);
    }
}
