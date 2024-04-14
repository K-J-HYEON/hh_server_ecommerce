package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.domain.payment.Payment;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.format.DateTimeFormatter;

@Schema(description = "결제 응답 DTO")
public record PaymentResponse(

        @Schema(description = "id")
        Long id,

        @Schema(description = "결제 금액")
        Long payAmount,

        @Schema(description = "결제 방법")
        String paymentMethod,

        @Schema(description = "결제 날짜")
        String paidAt
) {
    private final static DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static PaymentResponse from(Payment payment) {
        return new PaymentResponse(
                payment.id(),
                payment.payAmount(),
                payment.paymentMethod(),
                payment.paidAt().format(DATE_TIME_FORMATTER)
        );
    }
}
