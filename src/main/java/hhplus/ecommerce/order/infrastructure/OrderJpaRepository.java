package hhplus.ecommerce.order.infrastructure;

import hhplus.ecommerce.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
