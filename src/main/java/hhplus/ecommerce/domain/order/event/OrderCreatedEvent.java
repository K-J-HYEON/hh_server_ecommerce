package hhplus.ecommerce.domain.order.event;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.payment.Payment;
import hhplus.ecommerce.domain.product.Product;
import java.util.List;

public record OrderCreatedEvent(
        List<Product> products,
        List<OrderRequest.ProductOrderRequest> orderRequests,
        Order order,
        Payment payment
) {
}