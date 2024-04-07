package hhplus.ecommerce.product.infrastructure;

import hhplus.ecommerce.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
}
