package hhplus.ecommerce.domain.order.event;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.product.Stock;
import hhplus.ecommerce.storage.order.OrderStatus;
import java.util.List;

public record OrderFailedEvent(
        Order order,
        OrderStatus orderStatus,
        List<Stock> stocks,
        OrderRequest orderRequest
) {
}
