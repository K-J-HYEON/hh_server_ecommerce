package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.api.dto.CartItemResult;
import hhplus.ecommerce.api.dto.UnitCartItemResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "장바구니 상품 응답 DTO")
public record CartItemResponse(List<UnitCartItemResult> cartItems,
        Long totalPrice) {

    @Schema(description = "장바구니 상품")
    public static CartItemResponse from(CartItemResult cartItemResult) {
        return new CartItemResponse(
                cartItemResult.cartItems(),
                cartItemResult.totalPrice()
        );
    }
}