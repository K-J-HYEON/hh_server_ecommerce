package hhplus.ecommerce.order.dto.request;

import jakarta.validation.constraints.NotBlank;

public record Receiver(
        @NotBlank String name,
        @NotBlank String address,
        @NotBlank String phoneNunber
) {
}
