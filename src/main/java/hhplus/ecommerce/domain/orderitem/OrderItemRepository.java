package hhplus.ecommerce.domain.orderitem;

import hhplus.ecommerce.storage.orderitem.OrderItemStatus;

import java.util.List;

public interface OrderItemRepository {
//    List<OrderItem> createOrderItem(List<OrderItemEntity> orderItemEntities);

    List<OrderItem> findAllByOrderId(Long orderId);

    void updateStatus(OrderItem item, OrderItemStatus orderItemStatus);
}
