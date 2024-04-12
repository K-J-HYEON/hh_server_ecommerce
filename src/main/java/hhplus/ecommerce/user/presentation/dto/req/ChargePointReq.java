package hhplus.ecommerce.user.presentation.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ChargePointReq (
        @NotNull @Positive Long point
) {

}
