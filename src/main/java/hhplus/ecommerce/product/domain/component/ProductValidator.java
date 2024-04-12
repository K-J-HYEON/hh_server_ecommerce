package hhplus.ecommerce.product.domain.component;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.presentation.dto.request.OrderReq;
import hhplus.ecommerce.order.domain.component.OrderStatus;
import hhplus.ecommerce.order.domain.component.OrderUpdater;
import hhplus.ecommerce.product.domain.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductValidator {
    private final OrderUpdater orderUpdater;

    public ProductValidator(OrderUpdater orderUpdater) {
        this.orderUpdater = orderUpdater;
    }

    public void checkProductStockCount(Order order,
                                       List<Product> products,
                                       List<OrderReq.ProductOrderReq> productOrderReq) {
        for (OrderReq.ProductOrderReq orderReq : productOrderReq) {
            Product product = products.stream()
                    .filter(p -> p.productId().equals(orderReq.id()))
                    .findFirst()
                    .orElseThrow(() -> {
                        orderUpdater.changeStatus(order, OrderStatus.CANCELED);
                        return new EntityNotFoundException(orderReq.id() + "상품의 정보가 없습니다.");
                    });

            if (!product.isExistProductStock(orderReq.quantity())) {
                orderUpdater.changeStatus(order, OrderStatus.CANCELED);
                throw new IllegalArgumentException(product.productId() + "상품재고가 충분하지 않습니다.");
            }
        }
    }
}
