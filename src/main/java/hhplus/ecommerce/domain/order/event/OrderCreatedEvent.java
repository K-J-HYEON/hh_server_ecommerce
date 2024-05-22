package hhplus.ecommerce.domain.order.event;

import hhplus.ecommerce.domain.order.Order;

public record OrderCreatedEvent(
        Order order
) {
}