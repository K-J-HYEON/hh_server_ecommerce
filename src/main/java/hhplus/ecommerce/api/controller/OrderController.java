package hhplus.ecommerce.api.controller;

import hhplus.ecommerce.domain.order.OrderService;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.api.dto.request.OrderReq;
import hhplus.ecommerce.api.dto.response.OrderRes;
import hhplus.ecommerce.api.dto.response.ProductInfoRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상품 주문", description = "상품 주문 관련 api 입니다.")
@RestController
@RequestMapping("/ecommerce/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @Operation(summary = "상품 주문 메서드", description = "상품 주문 메서드입니다.", tags = "주문")	// (1)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ProductInfoRes.class))),
            @ApiResponse(responseCode = "400", description = "bad request operation", content = @Content(schema = @Schema(implementation = ProductInfoRes.class)))
    })
    @PostMapping("/{orderId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderRes order(@PathVariable Long userId,
                          @Valid @RequestBody OrderReq req) {
        Order order = orderService.order(userId, req);
        return OrderRes.from(order);
    }
}
