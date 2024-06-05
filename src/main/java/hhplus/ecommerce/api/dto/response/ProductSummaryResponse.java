package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.domain.product.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품정보 응답 DTO")
public record ProductSummaryResponse(

        @Schema(description = "id")
        Long id,

        @Schema(description = "이름")
        String name,

        @Schema(description = "가격")
        Long price)
{
    public static ProductSummaryResponse from(Product product) {
        return new ProductSummaryResponse(
                product.id(),
                product.name(),
                product.price()
        );
    }
}