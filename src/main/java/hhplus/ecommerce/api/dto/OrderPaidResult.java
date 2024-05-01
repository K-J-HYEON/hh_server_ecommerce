package hhplus.ecommerce.api.dto;

import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.domain.order.Order;
import java.time.LocalDateTime;

public record OrderPaidResult(
        Long orderId,
        Long payAmount,
        Receiver receiver,
        LocalDateTime orderedAt
) {
    public static OrderPaidResult from(Order order) {
        return new OrderPaidResult(
                order.id(),
                order.payAmount(),
                new Receiver(
                        order.receiverName(),
                        order.address(),
                        order.phoneNumber()
                ),
                order.orderedAt()
        );
    }
}
