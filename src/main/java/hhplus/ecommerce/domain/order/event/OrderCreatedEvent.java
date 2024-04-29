package hhplus.ecommerce.domain.order.event;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.user.User;

import java.util.List;

public record OrderCreatedEvent(
        User user,
        List<Product> products,
        OrderRequest orderRequest,
        Order order
) {
}