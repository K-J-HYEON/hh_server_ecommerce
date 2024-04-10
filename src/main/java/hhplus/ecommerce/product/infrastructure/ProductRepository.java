package hhplus.ecommerce.product.infrastructure;

import hhplus.ecommerce.order.domain.component.OrderStatus;
import hhplus.ecommerce.product.domain.Product;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long productId);

    List<Product> findByIdIn(List<Long> productIds);

    void updateStock(Product product);

    List<Product> readPopularSellingProducts(OrderStatus orderStatus,
                                         LocalDateTime startDate,
                                         LocalDateTime endDate,
                                         Pageable pageable);
}
