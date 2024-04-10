package hhplus.ecommerce.order.domain;

import hhplus.ecommerce.order.domain.component.OrderStatus;

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
