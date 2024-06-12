package hhplus.ecommerce.api.controller;

import hhplus.ecommerce.api.dto.OrderPaidResult;
import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.api.dto.response.OrderResponse;
import hhplus.ecommerce.application.OrderUseCase;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ecommerce/api/orders")
public class OrderController {
    private final OrderUseCase orderUseCase;

    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @Tag(name = "상품 주문 및 결제 API", description = "상품주문 & 결제 API입니다.")
    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse order(@PathVariable Long userId,
                               @Valid @RequestBody OrderRequest request) {
        OrderPaidResult orderPaidResult = orderUseCase.order(userId, request);
        return OrderResponse.from(orderPaidResult);
    }
}
