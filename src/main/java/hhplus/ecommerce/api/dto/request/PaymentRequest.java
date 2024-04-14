package hhplus.ecommerce.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Schema(description = "결제 요청 DTO")
public record PaymentRequest(

        @Schema(description = "주문 id")
        @NotNull Long orderId,

        @Schema(description = "결제 금액")
        @NotNull Long payAmount,

        @Schema(description = "결제 방법")
        @NotBlank String paymentMethod) {
}
