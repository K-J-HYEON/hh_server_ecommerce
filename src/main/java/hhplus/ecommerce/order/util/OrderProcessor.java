package hhplus.ecommerce.order.util;

import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.dto.request.OrderReq;
import hhplus.ecommerce.order.infrastructure.OrderRepository;
import hhplus.ecommerce.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor {

    private final OrderRepository orderRepository;

    public OrderProcessor(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order order(User user, OrderReq req) {
        return orderRepository.order(user, req);
    }
}