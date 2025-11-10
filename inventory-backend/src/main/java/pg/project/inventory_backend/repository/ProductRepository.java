package pg.project.inventory_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pg.project.inventory_backend.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
