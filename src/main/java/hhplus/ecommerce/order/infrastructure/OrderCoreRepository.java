package hhplus.ecommerce.order.infrastructure;


import hhplus.ecommerce.order.domain.Order;
import hhplus.ecommerce.order.presentation.dto.request.OrderReq;
import hhplus.ecommerce.order.presentation.dto.request.Receiver;
import hhplus.ecommerce.order.entity.OrderEntity;
import hhplus.ecommerce.order.domain.component.OrderStatus;
import hhplus.ecommerce.user.domain.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class OrderCoreRepository implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    public OrderCoreRepository(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order order(User user, OrderReq req) {
        Receiver receiver = req.receiver();
        OrderEntity orderEntity = new OrderEntity(
                user.userId(),
                req.paymentAmount(),
                receiver.name(),
                receiver.address(),
                receiver.phoneNunber(),
                OrderStatus.READY,
                LocalDateTime.now()
        );
        return orderJpaRepository.save(orderEntity).toOrder();
    }

    @Override
    public Order updateStatus(Order order, OrderStatus orderStatus) {
        OrderEntity orderEntity = orderJpaRepository.findById(order.orderId())
                .orElseThrow();
        orderEntity.updateStatus(orderStatus);
        return orderJpaRepository.save(orderEntity).toOrder();
    }

    @Override
    public Order findById(Long orderId) {
        return orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문정보를 찾지 못하였습니다. - id: " + orderId))
                .toOrder();
    }
}
