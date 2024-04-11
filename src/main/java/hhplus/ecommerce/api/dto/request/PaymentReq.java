package hhplus.ecommerce.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentReq(
        @NotNull Long orderId,
        @NotNull Long payAmount,
        @NotBlank String paymentMethod
) {
}
