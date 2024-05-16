package hhplus.ecommerce.domain.product.event;

import hhplus.ecommerce.api.dto.request.OrderRequest;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.product.Product;
import hhplus.ecommerce.domain.product.Stock;
import hhplus.ecommerce.domain.user.User;
import java.util.List;

public record DecreasedStockEvent(
        User user,
        Order order,
        List<Product> products,
        OrderRequest orderRequest,
        List<Stock> decreaseStock
) {
}