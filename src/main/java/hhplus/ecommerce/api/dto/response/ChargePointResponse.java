package hhplus.ecommerce.api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "충전 금액 응답 DTO")
public record ChargePointResponse(Long balance) {

    @Schema(description = "충전 금액")
    public static ChargePointResponse from(Long point) {
        return new ChargePointResponse(point);
    }
}