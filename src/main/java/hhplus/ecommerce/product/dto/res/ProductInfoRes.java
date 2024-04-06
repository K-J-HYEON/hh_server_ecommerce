package hhplus.ecommerce.product.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductInfoRes {
    private Long productId;
    private String name;
    private int price;

    private String size;
    private String color;
    private int stock;
}
