package hhplus.ecommerce.api.dto.response;

public record ChargePointRes(Long point) {
    public static ChargePointRes from(Long point) {
        return new ChargePointRes(point);
    }
}
