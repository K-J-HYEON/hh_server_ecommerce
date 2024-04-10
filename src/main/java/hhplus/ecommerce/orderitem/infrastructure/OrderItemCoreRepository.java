package hhplus.ecommerce.orderitem.infrastructure;

import hhplus.ecommerce.orderitem.Entity.OrderItemEntity;
import hhplus.ecommerce.orderitem.domain.OrderItem;

import java.util.List;

public class OrderItemCoreRepository implements OrderItemRepository {
    private final OrderItemJpaRepository orderItemJpaRepository;

    public OrderItemCoreRepository(OrderItemJpaRepository orderItemJpaRepository) {
        this.orderItemJpaRepository = orderItemJpaRepository;
    }

    @Override
    public List<OrderItem> createOrderItem(List<OrderItemEntity> orderItemEntities) {
        return orderItemJpaRepository.saveAll(orderItemEntities)
                .stream().map(OrderItemEntity::toOrderItem)
                .toList();
    }
}