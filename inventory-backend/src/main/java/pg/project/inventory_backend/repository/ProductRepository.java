package pg.project.inventory_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pg.project.inventory_backend.model.Product;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByDueDateBetween(LocalDate dueDateAfter, LocalDate dueDateBefore);
    List<Product> findByDueDateLessThanEqual(LocalDate limit);
}
