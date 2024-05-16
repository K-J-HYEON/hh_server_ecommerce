package hhplus.ecommerce.domain.product.event;

import hhplus.ecommerce.domain.order.Order;

public record StockDecreaseEventSuccess(Order order) {
}
