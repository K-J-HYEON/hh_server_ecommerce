package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.orderitem.OrderItemRepository;
import hhplus.ecommerce.storage.orderitem.OrderItemStatus;
import hhplus.ecommerce.storage.order.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdater {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderUpdater(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public void changeStatus(Order order, OrderStatus orderStatus) {
        orderRepository.updateStatus(order, orderStatus);
    }

    public void changeItemStatus(OrderItem item, OrderItemStatus orderItemStatus) {
        orderItemRepository.updateStatus(item, orderItemStatus);
    }
}
