package hhplus.ecommerce.product.dto.res;

import hhplus.ecommerce.product.domain.Product;

public record ProductInfoDetailRes(
        Long productId,
        String name,
        int price,
        int stock,
        String size,
        String color
) {
    public static ProductInfoDetailRes from(Product product) {
        return new ProductInfoDetailRes(
                product.productId(),
                product.name(),
                product.price(),
                product.stock(),
                product.size(),
                product.color()
        );
    }

}
