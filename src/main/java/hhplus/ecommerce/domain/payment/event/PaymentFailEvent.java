package hhplus.ecommerce.domain.payment.event;

import hhplus.ecommerce.domain.order.Order;

public record PaymentFailEvent(Order order) {
}
