package hhplus.ecommerce.domain.order.event;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.payment.Payment;

public record OrderCreatedEvent(
        Order order,
        Payment payment
) {
}