package hhplus.ecommerce.product.infrastructure;

import hhplus.ecommerce.order.domain.component.OrderStatus;
import hhplus.ecommerce.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByIdIn(List<Long> productIds);

    @Query("SELECT pe FROM ProductEntity pe JOIN OrderItemEntity oi ON pe.productId = oi.productId " +
            "JOIN OrderEntity oe ON oi.orderId = oe.productId " +
            "WHERE oe.orderStatus = :orderStatus " +
            "AND oe.orderedAt Between :startDate AND :endDate " +
            "GROUP BY pe.productId " +
            "ORDER BY SUM(oi.count) DESC")

    Page<ProductEntity> readPopularSellingProducts(@Param("orderStatus") OrderStatus orderStatus,
                                                @Param("startDate") LocalDateTime startDate,
                                                @Param("endDate") LocalDateTime endDate,
                                                Pageable pageable);
}