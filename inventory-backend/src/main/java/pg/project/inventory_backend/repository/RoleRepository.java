package pg.project.inventory_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pg.project.inventory_backend.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
