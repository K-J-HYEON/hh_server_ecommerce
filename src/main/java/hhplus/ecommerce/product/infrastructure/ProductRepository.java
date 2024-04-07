package hhplus.ecommerce.product.infrastructure;

import hhplus.ecommerce.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long productId);
}
