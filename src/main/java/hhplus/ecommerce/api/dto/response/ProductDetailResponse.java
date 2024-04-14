package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.domain.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 상세 정보 응답 DTO")
public record ProductDetailResponse(

        @Schema(description = "id")
        Long id,

        @Schema(description = "이름")
        String name,

        @Schema(description = "가격")
        Long price,

        @Schema(description = "주문 수량")
        Long stockQuantity,

        @Schema(description = "사이즈")
        String size,

        @Schema(description = "색상")
        String color
) {
    public static ProductDetailResponse from(Product product) {
        return new ProductDetailResponse(
                product.id(),
                product.name(),
                product.price(),
                product.stockCount(),
                product.size(),
                product.color()
        );
    }
}
