package hhplus.ecommerce.order.presentation;

import hhplus.ecommerce.order.application.OrderService;
import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.presentation.dto.request.OrderReq;
import hhplus.ecommerce.order.presentation.dto.response.OrderRes;
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

    @Tag(name = "상품 주문 및 결제 API", description = "상품주문 & 결제 API입니다.")
    @PostMapping("/{orderId}/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderRes order(@PathVariable Long userId,
                          @Valid @RequestBody OrderReq req) {
        Order order = orderService.order(userId, req);
        return OrderRes.from(order);
    }
}
