package pg.project.inventory_backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import pg.project.inventory_backend.model.Role;
import pg.project.inventory_backend.model.User;
import pg.project.inventory_backend.repository.RoleRepository;
import pg.project.inventory_backend.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataSeeder {
    @Bean
    @Order(1)
    CommandLineRunner seedDB( UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ROLE_USER").isEmpty())  roleRepository.save(new Role("ROLE_USER"));
            if (roleRepository.findByName("ROLE_MANAGER").isEmpty()) roleRepository.save(new Role("ROLE_MANAGER"));
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) roleRepository.save(new Role("ROLE_ADMIN"));
            if (userRepository.findByUsername("admin").isEmpty()) {
                User user = new User(
                        "admin",
                        "admin@gmail.com",
                        passwordEncoder.encode("admin123#_10")
                );
                // Default role = ROLE_USER
                Role userRole = roleRepository.findByName("ROLE_ADMIN")
                        .orElseThrow(() -> new RuntimeException("Default role not found"));

                Set<Role> roles = new HashSet<>();
                roles.add(userRole);
                user.setRoles(roles);
                userRepository.save(user);
            }
        };
    }
}
