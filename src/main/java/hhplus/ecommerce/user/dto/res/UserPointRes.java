package hhplus.ecommerce.user.dto.res;

public record UserPointRes(Long point) {
    public static UserPointRes from(Long point) {
        return new UserPointRes(point);
    }

}
