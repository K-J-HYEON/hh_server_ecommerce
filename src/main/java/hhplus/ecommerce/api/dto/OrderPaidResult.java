package hhplus.ecommerce.api.dto;

import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.payment.Payment;

import java.time.LocalDateTime;

public record OrderPaidResult(
        Long orderId,
        Long paymentId,
        Long payAmount,
        Receiver receiver,
        String paymentMethod,
        LocalDateTime orderedAt,
        LocalDateTime paidAt
) {
    public static OrderPaidResult of(Order order, Payment payment) {
        return new OrderPaidResult(
                order.id(),
                payment.id(),
                payment.payAmount(),
                new Receiver(
                        order.receiverName(),
                        order.address(),
                        order.phoneNumber()
                ),
                payment.paymentMethod(),
                order.orderedAt(),
                payment.paidAt()
        );
    }
}
