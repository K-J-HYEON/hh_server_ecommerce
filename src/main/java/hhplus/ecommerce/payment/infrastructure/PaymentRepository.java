package hhplus.ecommerce.payment.infrastructure;

import hhplus.ecommerce.payment.domain.Payment;

public interface PaymentRepository {
    Payment create(Long orderId, Long payAmount, String paymentMethod);
}