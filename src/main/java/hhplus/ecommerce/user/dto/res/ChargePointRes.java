package hhplus.ecommerce.user.dto.res;

public record ChargePointRes(Long point) {
    public static ChargePointRes from(Long point) {
        return new ChargePointRes(point);
    }
}
