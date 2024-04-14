package hhplus.ecommerce.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


@Schema(description = "받는 사람")
public record Receiver(

        @Schema(description = "이름")
        @NotBlank String name,

        @Schema(description = "주소")
        @NotBlank String address,

        @Schema(description = "휴대폰 번호   ")
        @NotBlank String phoneNumber
) {
}
