package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.api.dto.CartResult;
import io.swagger.v3.oas.annotations.media.Schema;

public record CartResponse(

        @Schema(description = "장바구니 id")
        Long id,

        @Schema(description = "사용자 id")
        Long userId
) {

    public static CartResponse from(CartResult cartResult) {
        return new CartResponse(
                cartResult.id(),
                cartResult.userId()
        );
    }
}
