package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.domain.payment.Payment;

import java.time.format.DateTimeFormatter;

public record PaymentRes(
        Long paymentId,
        Long payAmount,
        String paymentMethod,
        String paidAt
) {

    private final static DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static PaymentRes from(Payment payment) {
        return new PaymentRes(
                payment.paymentId(),
                payment.payAmount(),
                payment.paymentMethod(),
                payment.paidAt().format(DATE_TIME_FORMATTER)
        );
    }
}