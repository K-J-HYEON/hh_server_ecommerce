package hhplus.ecommerce.orderitem.infrastructure;

import hhplus.ecommerce.orderitem.Entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {
}
