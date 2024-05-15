package hhplus.ecommerce.storage.order;

import hhplus.ecommerce.domain.order.Order;
import hhplus.ecommerce.domain.order.OrderForm;
import hhplus.ecommerce.domain.order.OrderRepository;
import hhplus.ecommerce.domain.orderitem.OrderItem;
import hhplus.ecommerce.domain.user.User;
import hhplus.ecommerce.storage.orderitem.OrderItemEntity;
import hhplus.ecommerce.storage.orderitem.OrderItemJpaRepository;
import hhplus.ecommerce.storage.orderitem.OrderItemStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class OrderCoreRepository implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderItemJpaRepository orderItemJpaRepository;

    public OrderCoreRepository(OrderJpaRepository orderJpaRepository, OrderItemJpaRepository orderItemJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderItemJpaRepository = orderItemJpaRepository;
    }

//    @Override
//    public Order order(User user, OrderRequest request) {
//        Receiver receiver = request.receiver();
//        OrderEntity orderEntity = new OrderEntity(
//                user.id(),
//                request.payAmount(),
//                receiver.name(),
//                receiver.address(),
//                receiver.phoneNumber(),
//                OrderStatus.READY,
//                LocalDateTime.now()
//        );
//        return orderJpaRepository.save(orderEntity).toOrder();
//    }

    @Override
    public Order findById(Long orderId) {
        List<OrderItem> orderItems = orderItemJpaRepository.findAllByOrderId(orderId).stream()
                .map(OrderItemEntity::toOrderItem)
                .toList();

        return orderJpaRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("주문 정보를 찾지 못했습니다. - id: " + orderId))
                .toOrder(orderItems);
    }

    @Override
    public Order create(User user, OrderForm orderForm) {
        OrderEntity order = orderJpaRepository.save(new OrderEntity(
                user.id(),
                orderForm.payAmount(),
                orderForm.receiveName(),
                orderForm.address(),
                orderForm.phoneNumber(),
                OrderStatus.READY,
                LocalDateTime.now()
        ));

        List<OrderItem> items = orderItemJpaRepository.saveAll(
                        orderForm.orderProducts().stream()
                                .map(p -> new OrderItemEntity(
                                        order.getId(),
                                        p.productId(),
                                        p.productName(),
                                        p.unitPrice(),
                                        p.totalPrice(),
                                        p.quantity(),
                                        OrderItemStatus.CREATED
                                )).toList())
                .stream().map(OrderItemEntity::toOrderItem)
                .toList();

        return order.toOrder(items);
    }

    @Override
    public void updateStatus(Order order, OrderStatus orderStatus) {
        OrderEntity orderEntity = orderJpaRepository.findById(order.id())
                .orElseThrow(() -> new EntityNotFoundException("주문 정보를 찾지 못했습니다. - id: " + order.id()));
        orderEntity.updateStatus(orderStatus);
        orderJpaRepository.save(orderEntity);
    }
}
