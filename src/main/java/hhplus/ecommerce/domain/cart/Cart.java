package hhplus.ecommerce.domain.cart;

import java.time.LocalDateTime;

public record Cart(
        Long id,
        Long userId,
        LocalDateTime createAt,
        LocalDateTime updateAt
) {
}
