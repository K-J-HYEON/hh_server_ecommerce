package hhplus.ecommerce.order.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderReq(@Valid Receiver receiver,
                       @Valid List<ProductOrderReq> products,
                       @NotNull Long paymentAmount) {

    public record ProductOrderReq(
            @NotNull Long id,
            @NotNull Long quantity
    ) {
    }
}
