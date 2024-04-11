package hhplus.ecommerce.domain.order;


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
