package hhplus.ecommerce.order.component;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.infrastructure.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdater {
    private final OrderRepository orderRepository;

    public OrderUpdater(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order changeStatus(Order order, OrderStatus orderStatus) {
        return orderRepository.updateStatus(order, orderStatus);
    }
}
