package hhplus.ecommerce.domain.order;

import java.time.LocalDateTime;

public record Order(
        Long orderId,
        Long userId,
        Long payAmount,
        String receiverName,
        String address,
        String phoneNumber,
        String orderStatus,
        LocalDateTime orderedAt
) {
}
