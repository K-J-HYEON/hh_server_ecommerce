package hhplus.ecommerce.storage.order;

import hhplus.ecommerce.api.dto.request.OrderRequest;
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
    public Order order(User user, OrderRequest request) {
        Receiver receiver = request.receiver();
        OrderEntity orderEntity = new OrderEntity(
                user.id(),
                request.paymentAmount(),
                receiver.name(),
                receiver.address(),
                receiver.phoneNumber(),
                OrderStatus.READY,
                LocalDateTime.now()
        );
        return orderJpaRepository.save(orderEntity).toOrder();
    }

    @Override
    public Order updateStatus(Order order, OrderStatus orderStatus) {
        OrderEntity orderEntity = orderJpaRepository.findById(order.id())
                .orElseThrow();
        orderEntity.updateStatus(orderStatus);
        return orderJpaRepository.save(orderEntity).toOrder();
    }

    @Override
    public Order findById(Long orderId) {
        return orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문 정보를 찾지 못했습니다. - id: " + orderId))
                .toOrder();
    }
}
