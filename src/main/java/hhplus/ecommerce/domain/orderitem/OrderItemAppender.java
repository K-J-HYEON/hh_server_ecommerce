package hhplus.ecommerce.domain.orderitem;


import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.api.dto.request.OrderReq;
import hhplus.ecommerce.storage.orderitem.OrderItemEntity;
import hhplus.ecommerce.domain.product.Product;
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