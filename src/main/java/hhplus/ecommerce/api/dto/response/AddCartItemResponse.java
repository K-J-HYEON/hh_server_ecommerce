package hhplus.ecommerce.api.dto.response;

public record AddCartItemResponse(String message) {
    public static AddCartItemResponse from(String mesasge) {
        return new AddCartItemResponse(mesasge);
    }
}