package hhplus.ecommerce.orderitem.domain.component;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.dto.request.OrderReq;
import hhplus.ecommerce.orderitem.entity.OrderItemEntity;
import hhplus.ecommerce.orderitem.domain.OrderItem;
import hhplus.ecommerce.orderitem.infrastructure.OrderItemRepository;
import hhplus.ecommerce.product.domain.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemAppender {

    private final OrderItemRepository orderItemRepository;

    public OrderItemAppender(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> create(Order order,
                                  List<Product> products,
                                  List<OrderReq.ProductOrderReq> productOrderReqs) {
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();

        for (OrderReq.ProductOrderReq orderReq : productOrderReqs) {
            Product product = products.stream()
                    .filter(p -> p.productId().equals(orderReq.id()))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException(orderReq.id() + "상품정보를 찾지 못하였습니다."));

            orderItemEntities.add(
                    new OrderItemEntity(
                            order.orderId(),
                            product.productId(),
                            product.name(),
                            product.price(),
                            product.orderTotalPrice(orderReq.quantity()),
                            orderReq.quantity()
                    )
            );
        }
        return orderItemRepository.createOrderItem(orderItemEntities);
    }
}