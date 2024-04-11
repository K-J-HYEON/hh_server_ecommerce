package hhplus.ecommerce.domain.order;

import hhplus.ecommerce.api.dto.request.OrderReq;

public interface OrderService {
    Order order(Long userId, OrderReq req);
}
