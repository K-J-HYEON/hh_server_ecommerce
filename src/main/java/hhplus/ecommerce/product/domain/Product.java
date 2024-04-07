package hhplus.ecommerce.product.domain;

public record Product (
        Long productId,

        String name,

        int price,

        int stock,

        String size,

        String color
) {

}
