package hhplus.ecommerce.domain.product;

import hhplus.ecommerce.storage.order.OrderStatus;
import hhplus.ecommerce.storage.product.ProductEntity;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();

    Optional<ProductEntity> findById(Long productId);

    List<Product> findByIdIn(List<Long> productIds);

    void updateStock(Product product);

    List<Product> findTopSellingProducts(OrderStatus orderStatus,
                                         LocalDateTime startDate,
                                         LocalDateTime endDate,
                                         Pageable pageable);
}
