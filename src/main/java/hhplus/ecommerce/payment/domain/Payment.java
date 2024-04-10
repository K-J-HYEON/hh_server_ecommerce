package hhplus.ecommerce.payment.domain;

import java.time.LocalDateTime;

public record Payment(
        Long paymentId,
        Long orderId,
        Long payAmount,
        String paymentMethod,
        LocalDateTime paidAt
) {
}
