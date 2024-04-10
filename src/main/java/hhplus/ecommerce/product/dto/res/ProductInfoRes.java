package hhplus.ecommerce.product.dto.res;

import hhplus.ecommerce.product.domain.Product;
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
