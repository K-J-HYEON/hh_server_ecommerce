package hhplus.ecommerce.api.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "충전 응답 DTO")
public record ChargePointResponse(Long balance) {

    @Schema(description = "총 잔액")
    public static ChargePointResponse from(Long balance) {
        return new ChargePointResponse(balance);
    }
}
