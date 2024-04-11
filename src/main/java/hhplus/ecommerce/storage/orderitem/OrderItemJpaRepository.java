package hhplus.ecommerce.storage.orderitem;

import hhplus.ecommerce.storage.orderitem.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {
}
