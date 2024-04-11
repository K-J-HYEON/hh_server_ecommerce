package hhplus.ecommerce.api.dto.response;

public record UserPointRes(Long point) {
    public static UserPointRes from(Long point) {
        return new UserPointRes(point);
    }

}
