package hhplus.ecommerce.order.util;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.infrastructure.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderRetrieve {

    private final OrderRepository orderRepository;

    public OrderRetrieve(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order retrieveByOrderId(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
