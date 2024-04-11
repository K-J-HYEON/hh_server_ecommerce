package hhplus.ecommerce.domain.payment;

import java.time.LocalDateTime;

public record Payment(
        Long paymentId,
        Long orderId,
        Long payAmount,
        String paymentMethod,
        LocalDateTime paidAt
) {
}
