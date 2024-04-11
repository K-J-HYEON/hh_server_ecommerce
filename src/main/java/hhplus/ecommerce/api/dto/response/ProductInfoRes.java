package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public record ProductInfoRes(
        Long productId,
        String name,
        Long price) {
    public static ProductInfoRes from(Product product) {
        return new ProductInfoRes(
                product.productId(),
                product.name(),
                product.price()
        );
    }
}
