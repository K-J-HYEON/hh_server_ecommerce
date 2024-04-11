package hhplus.ecommerce.storage.order;


import hhplus.ecommerce.api.dto.request.OrderReq;
import hhplus.ecommerce.api.dto.request.Receiver;
import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderRepository;
import hhplus.ecommerce.domain.user.User;
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
