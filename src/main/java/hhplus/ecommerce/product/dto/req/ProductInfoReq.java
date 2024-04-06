package hhplus.ecommerce.product.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ProductInfoReq {
    private Long productId;
    private String name;
    private int price;

    public ProductInfoReq(Long productId) {
        this.productId = productId;
    }
}
