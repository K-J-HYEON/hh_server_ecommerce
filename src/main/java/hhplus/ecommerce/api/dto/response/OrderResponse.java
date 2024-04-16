package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.api.dto.OrderPaidResult;
import hhplus.ecommerce.api.dto.request.Receiver;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.format.DateTimeFormatter;

@Schema(description = "주문 응답 DTO")
public record OrderResponse(

        @Schema(description = "주문 id")
        Long orderId,

        @Schema(description = "결제 id")
        Long paymentId,

        @Schema(description = "결제 금액")
        Long payAmount,

        @Schema(description = "받는 사람")
        Receiver receiver,

        @Schema(description = "결제 방법")
        String paymentMethod,

        @Schema(description = "주문 날짜")
        String orderedAt,

        @Schema(description = "겳제 날짜")
        String paidAt
) {
    private final static DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static OrderResponse from(OrderPaidResult orderPaidResult) {
        return new OrderResponse(
                orderPaidResult.orderId(),
                orderPaidResult.paymentId(),
                orderPaidResult.payAmount(),
                orderPaidResult.receiver(),
                orderPaidResult.paymentMethod(),
                orderPaidResult.orderedAt().format(DATE_TIME_FORMATTER),
                orderPaidResult.paidAt().format(DATE_TIME_FORMATTER)
        );
    }
}
