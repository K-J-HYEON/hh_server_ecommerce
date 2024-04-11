package hhplus.ecommerce.orderitem.infrastructure;

import hhplus.ecommerce.orderitem.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {
}
