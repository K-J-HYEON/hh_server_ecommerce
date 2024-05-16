package hhplus.ecommerce.domain.payment.event;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.payment.Payment;

public record PaymentSuccessEvent(Order order, Payment payment) {
}
