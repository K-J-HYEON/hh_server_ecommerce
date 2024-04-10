package hhplus.ecommerce.order.domain;

import java.time.LocalDateTime;

public record Order(
        Long orderId,
        Long userId,
        Long payAmount,
        String receiverName,
        String address,
        String phoneNumber,
        String orderStatus,
        LocalDateTime orderedAt,
        String productName,
        Long count,
        Long price,
        Long totalPrice
) {
}
