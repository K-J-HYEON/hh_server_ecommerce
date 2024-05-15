package hhplus.ecommerce.api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "주문 요청 DTO")
public record OrderRequest(

        @Schema(description = "받는 사람")
        @Valid Receiver receiver,

//        @Schema(description = "상품")
//        @Valid List<ProductOrderRequest> products,

        @Schema(description = "결제 금액")
        @NotNull Long paymentAmount,

        @Schema(description = "결제 방법")
        @NotBlank String paymentMethod) {

//    @Schema(description = "상품 주문 요청 DTO")
//    public record ProductOrderRequest(
//            @Schema(description = "id")
//            @NotNull Long id,
//
//            @Schema(description = "주문 수량")
//            @NotNull Long orderCount
//    ) {
//    }
}
