package hhplus.ecommerce.order.component;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.infrastructure.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderReader {

    private final OrderRepository orderRepository;

    public OrderReader(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order retrieveByOrderId(Long orderId) {
        return orderRepository.findById(orderId);
    }
}
