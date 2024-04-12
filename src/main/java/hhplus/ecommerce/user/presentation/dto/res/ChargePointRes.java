package hhplus.ecommerce.user.presentation.dto.res;

public record ChargePointRes(Long point) {
    public static ChargePointRes from(Long point) {
        return new ChargePointRes(point);
    }
}
