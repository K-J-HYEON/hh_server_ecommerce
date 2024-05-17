package hhplus.ecommerce.api.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "잔액 응답 DTO")
public record BalanceResponse(Long balance) {

    public static BalanceResponse from(Long balance) {
        return new BalanceResponse(balance);
    }
}