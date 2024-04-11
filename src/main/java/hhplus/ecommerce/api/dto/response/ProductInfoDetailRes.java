package hhplus.ecommerce.api.dto.response;

import hhplus.ecommerce.domain.product.Product;

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
