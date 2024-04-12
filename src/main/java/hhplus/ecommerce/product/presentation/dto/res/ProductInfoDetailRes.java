package hhplus.ecommerce.product.presentation.dto.res;

import hhplus.ecommerce.product.domain.Product;

public record ProductInfoDetailRes(
        Long productId,
        String name,
        Long price,
        Long stockCount,
        String size,
        String color
) {
    public static ProductInfoDetailRes from(Product product) {
        return new ProductInfoDetailRes(
                product.productId(),
                product.name(),
                product.price(),
                product.stockCount(),
                product.size(),
                product.color()
        );
    }

}
