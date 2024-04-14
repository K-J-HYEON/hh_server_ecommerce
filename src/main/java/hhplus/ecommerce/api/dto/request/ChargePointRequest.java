package hhplus.ecommerce.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Schema(description = "충전 금액 요청 DTO")
public record ChargePointRequest(
        @NotNull
        @Positive
        @Schema(description = "충전 금액")
        Long point
) {
}
