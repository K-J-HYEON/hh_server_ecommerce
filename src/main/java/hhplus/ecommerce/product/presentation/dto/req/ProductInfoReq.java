package hhplus.ecommerce.product.presentation.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductInfoReq {
    private Long productId;
    private String name;
    private int price;

    public ProductInfoReq(Long productId) {
        this.productId = productId;
    }
}