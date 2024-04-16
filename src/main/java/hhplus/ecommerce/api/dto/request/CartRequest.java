package hhplus.ecommerce.api.dto.request;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

public record CartRequest(
        Long id,
        Long userId,
        LocalDateTime createAt,
        LocalDateTime updateAt,

        @Valid List<OrderRequest.ProductOrderRequest> products
) {
}
