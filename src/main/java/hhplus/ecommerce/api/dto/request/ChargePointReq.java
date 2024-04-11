package hhplus.ecommerce.api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ChargePointReq (
        @NotNull @Positive Long point
) {

}
